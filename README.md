# StaticDataTesting
开源协议：Apache License 2.0
项目仓库：https://github.com/yourusername/StaticTestDataGen
1. 功能概述
StaticTestDataGen 是一个轻量级测试数据生成工具，主要功能包括：

生成结构化测试数据（用户信息、产品数据等）
支持 CSV、JSON、XML 三种输出格式
可自定义数据模型和生成规则
适用于单元测试、集成测试和性能测试场景
2. 技术架构
编程语言：Java 8+
设计模式：工厂模式（支持扩展更多格式）
核心组件：
TestDataGenerator 接口：定义数据生成规范
CsvTestDataGenerator/JsonTestDataGenerator/XmlTestDataGenerator：格式实现类
TestDataGeneratorFactory：工厂类，根据格式类型创建生成器
TestDataApp：命令行入口
3. 安装与使用
3.1 环境要求

JDK 8 或更高版本
Eclipse IDE（可选，用于开发）
Maven（可选，用于构建）

3.2 获取源码

bash
git clone https://github.com/yourusername/StaticTestDataGen.git
cd StaticTestDataGen

3.3 编译项目

bash
javac -d bin src/com/generator/*.java

3.4 运行程序

bash
java -cp bin com.generator.TestDataApp CSV test.csv 100

参数说明：

第一个参数：输出格式（CSV/JSON/XML）
第二个参数：输出文件名
第三个参数：生成记录数量
4. 示例用法
4.1 生成 CSV 格式数据

bash
java -cp bin com.generator.TestDataApp CSV users.csv 500

4.2 生成 JSON 格式数据

bash
java -cp bin com.generator.TestDataApp JSON products.json 100

4.3 生成 XML 格式数据

bash
java -cp bin com.generator.TestDataApp XML orders.xml 200
5. 自定义扩展
5.1 添加新的数据格式

创建新的生成器类实现TestDataGenerator接口
在TestDataGeneratorFactory中添加新格式的创建逻辑
更新TestDataFormat枚举类

5.2 自定义数据模型
修改CsvTestDataGenerator、JsonTestDataGenerator等类中的数据生成逻辑，例如：

java
// 添加更多字段或修改生成规则
private static final String[] DEPARTMENTS = {"HR", "IT", "Finance", "Marketing"};
record.put("department", DEPARTMENTS[random.nextInt(DEPARTMENTS.length)]);
6. 项目结构
plaintext
StaticTestDataGen/
├── src/                    # 源代码
│   └── com/
│       └── generator/
│           ├── TestDataApp.java        # 入口类
│           ├── TestDataGenerator.java  # 生成器接口
│           ├── TestDataFormat.java     # 格式枚举
│           ├── TestDataGeneratorFactory.java  # 工厂类
│           ├── CsvTestDataGenerator.java      # CSV生成器
│           ├── JsonTestDataGenerator.java     # JSON生成器
│           └── XmlTestDataGenerator.java      # XML生成器
├── bin/                    # 编译输出目录
├── LICENSE                 # 开源许可证
└── README.md               # 项目说明
7. 贡献指南
Fork 本仓库
创建新分支：git checkout -b feature/new-format
提交代码并创建 Pull Request
遵守代码风格规范和测试要求
8. 常见问题
Q1：运行时提示 "找不到主类"
A：确保使用完整类名并正确设置类路径：

bash
java -cp bin com.generator.TestDataApp ...

Q2：如何添加更多数据字段？
A：修改相应生成器类中的数据生成逻辑，例如在CsvTestDataGenerator中添加新列。

Q3：能否集成到 Maven 项目？
A：可以，添加以下依赖到pom.xml：

xml
<dependency>
    <groupId>org.json</groupId>
    <artifactId>json</artifactId>
    <version>20231013</version>
</dependency>
