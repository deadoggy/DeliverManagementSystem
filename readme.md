
## 环境

- Idea 2016.3
- Maven
- Tomcat 9.0.0.M19
- Spring[详情见pom.xml]
- Hibernate

## 目录
```
.
├── out
│   ├── artifacts
│   └── production
├── pom.xml
├── readme.md
├── Server
│   ├── Build
│   └── src
├── SmartDeliver.iml
├── src
│   ├── main
│   └── test
└── target
    └── generated-sources
```

out文件是idea的artifact输出文件，其中artifacts中项目是tomcat的webapp的配置路径

Server是Tomcat 9.0.0.M19服务器，Build是服务器，src内是源码，有需要可以进行调试

src是项目源码 ，main下有webapp, java, resource文件，分别对应web应用，java源代码和资源文件。持久花在resource/META-INF中配置，webapp/WEB-INF中配置了基本的web.xml和dispatcher-Servlet。

target是maven的编译输出目录。 
