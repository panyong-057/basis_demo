package com.example.algorithm.array;

import com.haozhi.machinestatu.fengjisystem.log.LogManager;
import com.haozhi.machinestatu.fengjisystem.byteModel.ControlModel;
import com.haozhi.machinestatu.fengjisystem.utils.Hex2ByteUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 组包和解包的工具类
 */
public class Make_ParseBytePackageUtil {

	public  static final String READ="READ";
	public  static final String SET="SET";
	public  static final String ALARM="ALARM";
	private static final String TAG = Make_ParseBytePackageUtil.class.getSimpleName();

	/**
	 * 解数据包的过程 1、判断数据包完整性 2、去转义 3、去包头、包尾 4、验证检验单元，检验成功后去掉数据包中表示检验单元的2个字节 5、开始解包
	 * 5.1判断应答标志: 非00:操作不成功，则根据应答标志提示不成功的原因！ 00:操作成功 5.2操作成功时的解包 5..2.1解包开始位置
	 * 00应答标志后面，直到最后 5.2.2如何截取表示每个功能的数据段 （1）先获取功能字段的数据字节长度， 第一个字节
	 * （2）通过获取到的长度字节计算所要截取的功能字段的截取位置 起始位:长度字节位（它所在数组的下标），开始
	 * 结束位:长度字节所在下标+长度字节数据表示的长度 如果存在多个功能字段，则重复5.2.2这个过程
	 */
	public static List<String> paserPackage(byte[] byteData) {
		// 判断包的完整性
		boolean completeData = DataZhuanYiUtil.isCompleteData(byteData);
		if (!completeData) {
			return null;
			// 不是完整的包
		}
		// 转义并去掉开始、结束标志
		Byte[] deleteZhuanYiByteData = DataZhuanYiUtil
				.deleteZhuanYiByteData(byteData);
		// 进行校验
		byte[] doCRC = ToDoCRCUtil.afterRecivedDataDoCRC(deleteZhuanYiByteData);
		if (doCRC != null) {
			// 进行解包
			List<String> codeList = new ArrayList<String>();
			if (doCRC.length<12||doCRC.length==12){
				LogManager.e(TAG,"doCRC.length<12");
				return null;
			}
			if (doCRC[12] == 0x00) {// 查询成功
				if (doCRC.length<14||doCRC.length==14){
					LogManager.e(TAG,"doCRC.length<14");
					return null;
				}
				if (doCRC[14] == 9 && doCRC[15] == 0) {// 如果是查询全部参数的回包
					for (int i = 14; i < doCRC.length; i = i + 2) {
						byte[] d = new byte[2];
						// 由于高位在前的缘故，要进行字节处理
						d[0] = doCRC[i + 1];
						d[1] = doCRC[i];
						codeList.add(Hex2ByteUtil.bytesToHexString(d));
					}
				} else {
					// 非查询全部的回包
					for (int i = 13; i < doCRC.length; i++) {
						int b = doCRC[i];
						if (b<4){
							LogManager.e(TAG,"解包异常：功能字段长度字节部分的值小于4，请检查数据包字段定义,\n数据包："+ Hex2ByteUtil.bytesToHexString(doCRC));
							return codeList;
						}
						byte[] data = new byte[b];
						System.arraycopy(doCRC, i, data, 0, b);
						byte[] realData = FuntionParkParseUtil.getRealData(data);
						i = i + b - 1;// 移动数组下标位置
						codeList.add(Hex2ByteUtil.bytesToHexString(realData));
						LogManager.e("第"+codeList.size()+"个功能字段", Hex2ByteUtil.bytesToHexString(data));
					}
					return codeList;
				}
			} else {
				String error = ResponErrorUtil.findError(deleteZhuanYiByteData[12]);
				LogManager.e(TAG,"响应异常：" + error);
				return null;
			}
			return codeList;
		} else {
			LogManager.e(TAG, "校验不正确");
			return null;
		}


	}

	/**
	 * 组数据包的过程 1、因为请求开始的协议类型等数据都是一样，所以可以固定写死，位置为开始标志位的后一位开始到请求位之前 2、加入请求标志
	 * 3、用0xff填充应答标志 4、加入功能字段 4.1如何添加功能字段 4.1.1计算长度位 先获取要发送的功能表示的值 4.1.2添加功能ID
	 * 在长度位后面加上两个字节的长度功能id 4.1.3使用00补足长度字节表示的功能字段的长度 如
	 * 长度是04，功能id是0405，现在长度字节+功能字节的长度是3 那么只需要补一个字节的00即可，就要变成04040500 如
	 * 长度是08，功能id是0603，现在长度字节+功能字节的长度是3，要在后面补上5个字节的00
	 * 那么就是要变成08+0603+0000000000===》0806030000000000 如果有多个功能字段请重复4过程 5.添加校验单元
	 * 添加完成功能字段后，使用全部数据去生成一个2字节的CRC16校验， 最后将得到的校验放到前面组的数据的后面。 6、将要发送的数据进行转义
	 * 在添加完检验单元的数据中，按照转义规则进行转义 7、添加开始和结束的标志
	 */
	public static String makePackage(List<ControlModel> list,String mesure) {

		// 组数据包
		byte[] byteData = Hex2ByteUtil.hexString2Bytes(BytePackageUtil.makePckage(
				list, mesure));
		// 计算校验
		int getCrc16 = CRC16Util.GetCrc16(byteData);
		byte[] intToBytes = Hex2ByteUtil.intToBytes(getCrc16);
		// 合并校验
		byte[] concat = Hex2ByteUtil.concat(byteData, intToBytes);
		// 进行转义
		Byte[] zhuanYiByteData = DataZhuanYiUtil.addZhuanYiByteData(concat);

		/*System.out
				.println(Hex2ByteUtil.bytesToHexString(zhuanYiByteData).toUpperCase());*/
		return Hex2ByteUtil.bytesToHexString(zhuanYiByteData).toUpperCase();
	}

	public static void main(String[] args) {
		// makePackage();
		 String string="7E010100000000FFB80280010300040304010A007E";
		//String string = "7E010100000000FF7F008001020004010518040304000405040005500DBC0221F27E";
		// String string="7E010100000000FFB80280010300040304010A007E";
		// String string="7E010100000000FF1D02800103FF05500DF40153D27E";
		// String
		// string="7E010101000000FF0900800102007B090002010A00010102014204430444044504C504CE0414051A0511050305C504CE04C704C804C904CA04CB04CC04CD04D004D104060E070E080E090E0A0E0B0E0C0E0D0E0E0E0F0E100E110E120E130E150E160E0105D005D105D205D305C505C605C705C805B203B303B403B503B603B703B803B903BA03BB0389047E";
		paserPackage(Hex2ByteUtil.hexStringToBytes(string));
	}
}
