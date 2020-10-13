

错误
================================
Android项目 Invalid byte 3 of 3-byte UTF-8 sequence 错误
在 org.gradle.jvmargs= 后面加上-Dfile.encoding=UTF-8 参数，定义所有文件的编码为 UTF-8 问题解决

git
==================================
gitk 启动图形查看模式

git 文件回滚
- 场景1：当你改乱了工作区某个文件的内容，想直接丢弃工作区的修改时，用命令git checkout -- file。
- 场景2：当你不但改乱了工作区某个文件的内容，还添加到了暂存区时，想丢弃修改，分两步，
  - 第一步用命令git reset HEAD file，就回到了场景1，
  - 第二步按场景1操作。
- 场景3：已经提交了不合适的修改到版本库时，想要撤销本次提交，使用git reset --hard HEAD^。
