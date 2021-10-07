# W03
## 任务一
根据`SteganographyFactory.main()`和`Scene.main()`分为Encode和Decode两个步骤理解。
第一步进行Encode：
`SteganographyFactory.main()`调用`SteganographyFactory.getSteganography(String classSource, String originImage)`函数读取一个.java的代码源文件和一个源图像文件，将源代码编译为字节码后通过`SteganographyEncoder.encodeFile(File file)`函数读取编译后的`.class`文件中的字节流，连同name和size信息形成要隐写的`finalBytes`，用私有方法`encode(byte[] bytes)`生成隐写后的图像，再由`Factory`写入到新的图像文件中。

第二步进行Decode：
`Scene.main()`中用隐写后的图像创建一个`SteganographyClassLoader`，调用其`loadClass(String name)`方法时，自定义类加载器是最外层的类加载器，会逐层调用ApplicationClassLoader、PlatformClassLoader、BootstrapClassLoader的`loadClass(String name)`。BootstrapClassLoader进行`loadClass(String name) -> findClass(String name)`失败后依次进行PlatformClassLoader、ApplicationClassLoader、SteganographyClassLoader的`findClass(String name)`。
`SteganographyClassLoader.findClass(String name)`从url读取图片，用encoder解码成相当于`.class`的字节流，`defineClass()`返回定义好的类型。

通过加载后打印实际上的ClassLoader尝试验证：
```java
SteganographyClassLoader loader = new SteganographyClassLoader(new URL("file:./example.QuickSorter.png"));
Class c = loader.loadClass("example.QuickSorter");
System.out.println(c.getClassLoader());
```
当QuickSorter.class保留在同项目`example/`目录下时，这里c.getClassLoader()依然是AppClassLoader而非
自定义的SteganographyClassLoader，并没有真正从图片加载。只有当QuickSorter.class从本项目中移除后这里才会打印SteganographyClassLoader，说明是优先使用父ClassLoader（内层），失败时才会使用子ClassLoader（外层）。

## 任务二

原图：

![](https://tva1.sinaimg.cn/large/008i3skNgy1gv6qyy1g7gj60p00e642502.jpg)

隐写后：

QuickSorter: https://github.com/jwork-2021/jw03-XinlanGu1998/blob/main/S202220005/example.QuickSorter.png

HeapSorter: https://github.com/jwork-2021/jw03-XinlanGu1998/blob/main/S202220005/example.HeapSorter.png
## 任务三
排序结果：
QuickSorter:

![](https://tva1.sinaimg.cn/large/008i3skNgy1gv6r5g9qmrj60j609emy002.jpg)

HeapSorter:

![](https://tva1.sinaimg.cn/large/008i3skNgy1gv6qxmf1k1j60j60aaab602.jpg)

## 任务四

冒昧借用了[@halipai同学](https://github.com/jwork-2021/jw03-halipai/tree/main/jw03-halipai)的SelectSorter:

排序结果：

![](https://tva1.sinaimg.cn/large/008i3skNgy1gv6rfn3fhpj60j805m3yw02.jpg)