## 验证码
#### DefaultKaptcha，先配置好config，将config设置到DefaultKaptcha中就可以使用

#### pom中加入
```xml
  <dependency>
    <groupId>com.github.penggle</groupId>
    <artifactId>kaptcha</artifactId>
    <version>2.3.2</version>
  </dependency>
```
#### properties文件参考
```properties
kaptcha.producer.impl=com.google.code.kaptcha.impl.WaterRipple
kaptcha.border=no
kaptcha.border.color=blue
kaptcha.textproducer.font.color=blue
kaptcha.image.width=150
kaptcha.image.height=50
kaptcha.textproducer.font.size=42
kaptcha.textproducer.char.space=3
kaptcha.session.key=code
kaptcha.textproducer.char.length=6
kaptcha.noise.color=blue
kaptcha.noise.impl=com.google.code.kaptcha.impl.DefaultNoise
kaptcha.textproducer.font.names=宋体,楷体,微软雅黑
kaptcha.obscurificator.impl=com.google.code.kaptcha.impl.WaterRipple
```
