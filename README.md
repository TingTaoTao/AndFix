# AndFix
Android项目热修复

命令 : apkpatch.bat -f new.apk -t old.apk -o output1 -k debug.keystore -p android -a androiddebugkey -e android
-f <new.apk> ：新版本
-t <old.apk> : 旧版本
-o <output> ： 输出目录
-k <keystore>： 打包所用的keystore
-p <password>： keystore的密码
-a <alias>： keystore 用户别名
-e <alias password>： keystore 用户别名密码

上面输入的命令行：
apkpatch -f 2.apk -t 1.apk -o output1 -k qiushi.jks -p 123456 -a qiushi -e 123456 
命令行意思：
apkpatch -f new.apk -t old.apk -o output1 -k 签名文件 -p 签名密码 -a 机构名 -e 机构签名密码

如无错误，编译后会生成一个apatch文件，改名成out.apatch

adb push E:\android_fix\patch_file\out.apatch /sdcard/