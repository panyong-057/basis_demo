## 项目分类
1、designpatterns  设计模式
=================================================
+ 设计模式六大原则
    + 单一职责原则  一个类只负责一项职责。
    + 里氏替换原则  子类可以扩展父类的功能，但不能改变父类原有的功能
    + 依赖倒置原则  程序要依赖于抽象接口，不要依赖于具体实现
    + 接口隔离原则  接口拆分
    + 迪米特原则    少知原则   一个对象应该对其他对象保持最少的了解。
    + 开闭原则      对扩展开放，对修改关闭


+ 计模式分为三大类
    + 创建型模式，共五种：工厂方法模式、抽象工厂模式、单例模式、建造者模式、原型模式。
    + 结构型模式，共七种：适配器模式、装饰器模式、代理模式、外观模式、桥接模式、组合模式、享元模式。
    + 行为型模式，共十一种：策略模式、模板方法模式、观察者模式、迭代子模式、责任链模式、命令模式、
                 备忘录模式、状态模式、访问者模式、中介者模式、解释器模式。



2、openssldemo  openssl
=================================================
  [openssldemo具体内容在这](https://github.com/panyong-057/openssldemo).
 + openssl 编译脚本
 + SM 国密家族
 + Base64
 + RSA
 + AES


3、weixingsign  微信签名
=================================================
 + 微信支付 签名生产工程


4、algorithm  算法
=================================================
+ 数组 -
  + 数组拼接
  + 排序
+ 链表 -
  + 遍历
  + 链表反转
+ 树 -
  + 二叉树遍历
  + 镜像反转
+ 散列 -
  + 自定义hashMap
  + LRU 算法实现
+ 队列
+ 栈
  + 
+ 堆
+ 图

5、animation  动画
=================================================
+ 属性动画

6、Android 源码解读（可参考邓凡平的书籍及最新源码）
=================================================
  SystemServer 进程开启的那些服务
  + 1、 EntropyService， 熵服务，用于产生随机数；
  + 2、 PowerManagerService， 电源管理服务；PMS
  + 3、 ActivityMangerService Activity，管理服务； AMS
  + 4、 TelephonyRegistry， 电话服务，电话底层通知服务；
  + 5、 PackageManagerService， 程序包管理服务；PkMS
  + 6、 AccountManagerService， 联系人账户管理服务；
  + 7、 ContentService， 内容提供器服务，提供跨进程数据交换；
  + 8、 LightService， 光感应传感器服务；
  + 9、 BatteryService，电池服务；
  + 10、 VibrateService，震动器服务；
  + 11、 AlarmManagerService，闹钟服务；
  + 12、 WindowManagerService，窗口管理服务； WMS
  + 13、 BluetoothService，蓝牙服务；
  + 14、 InputMethodManagerService，输入法服务；
  + 15、 AccessibilityManagerService， 辅助器管理程序服务；
  + 16、 DevicePolicyManagerService，提供系统级别的设置及属性；
  + 17、 StatusBarManagerService，状态栏管理服务；
  + 18、 ClipboardService，粘贴板服务；
  + 19、 NetworkManagerService，网络管理服务；
  + 20、 TextServicesManagerService，用户管理服务；
  + 21、 NetworkStatsService，手机网络状态服务；
  + 22、 NetworkPolicyManagerService，low-level网络策略服务；
  + 23、 WifiP2pService，Wifi点对点服务；
  + 24、 WifiService，Wifi服务；
  + 25、 ConnectivityService，网络连接状态服务；
  + 26、 MountService，磁盘加载服务；
  + 27、 NotificationManagerService，通知管理服务；
  + 28、 DeviceStorageMonitorService，存储设备容量监听服务；
  + 29、 LocationManagerService，位置管理服务；
  + 30、 CountryDetectorService，检查用户当前所在国家服务；
  + 31、 SearchManagerService，搜索管理服务；
  + 32、 DropBoxManagerService，系统日志文件管理服务；
  + 33、 WallpaperManagerService，壁纸管理服务；
  + 34、 AudioService，AudioFlinger上层封装音频管理服务；
  + 35、 UsbService，USB Host 和 device管理服务；
  + 36、 UiModeManagerService，UI模式管理服务；
  + 37、 BackupManagerService，备份服务；
  + 38、 AppWidgetService，应用桌面部件服务；
  + 39、 RecognitionManagerService，身份识别服务；
  + 40、 DiskStatsService，磁盘统计服务；
  + 41、 SamplingProfilerService，性能统计服务；
  + 42、 NetworkTimeUpdateService，网络时间更新服务；
  + 43、 InputManagerService，输入管理服务；
  + 44、 FingerprintService，指纹服务；
  + 45、 DreamManagerService， dreams服务；
  + 46、 HdmiControlService， HDMI控制服务；
  + 47、 SsvService，SIM卡状态和转换服务；

