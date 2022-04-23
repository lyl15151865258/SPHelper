# SPHelper
跨进程的SharedPreferences工具类
基于https://github.com/penglu20/SPHelper 项目的完善
解决原项目bug：
原项目remove()方法不能正常移除键值对，原因是在SPHelper类中，remove()方法拼接URI时传递的类型值为TYPE_LONG，本项目已新增并修改为TYPE_DELETE；
新增功能：
由于我自己的项目需要分文件存储，因此对于原作者的每个方法都进行了重载：
// 存储到默认xml文件（文件名为SPHelperImpl类中的DEFAULT_NAME）
SPHelper.save("aaa", 1);
// 存储到指定名称的xml
SPHelper.save("myConfig", "aaa", 1);
// 移除默认xml文件的key-value
SPHelper.remove("bbb");
// 移除指定xml文件的key-value
SPHelper.remove("myConfig", "bbb");
// 清除默认xml所有key-value
SPHelper.clear();
// 清除指定xml所有key-value
SPHelper.clear("myConfig");
