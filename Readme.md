# Thymeleaf Layout Annotation

This package provides an annotation and interceptor that add layouts support to
[Thymeleaf](http://thymeleaf.org/) under [Spring](http://spring.io/).

# Install

Using Maven (or something compatible with Maven repositories):

```xml
<dependencies>
  <dependency>
    <groupId>com.jrfom</groupId>
    <artifactId>thymeleaf-layout-interceptor</artifactId>
    <version>0.1.0</version>
  </dependency>
</dependencies>

<repositories>
  <repository>
    <id>jsumners-github-releases</id>
    <url>https://github.com/jsumners/mvn-repo/raw/master/releases/</url>
  </repository>
  <repository>
    <id>jsumners-github-snapshots</id>
    <url>https://github.com/jsumners/mvn-repo/raw/master/snapshots/</url>
  </repository>
</repositories>
```

# Usage

First, add the interceptor to your application:

```java
public class ApplicationContextConfig extends WebMvcConfigurerAdapter {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    ThymeleafLayoutInterceptor layoutInterceptor = new ThymeleafLayoutInterceptor();
    layoutInterceptor.setDefaultLayout("layout"); // WEB-INF/templates/layout.html
    layoutInterceptor.setViewAttributeName("view"); // the default
    registry.addInterceptor(layoutInterceptor);

    super.addInterceptors(registry);
  }
}
```

Next, create a layout template:

```html
<html>
<head></head>
<body>
  <header><h1>Example Layout</h1></header>

  <section id="viewContainer">
    <div th:replace="views/__${view}__ :: content" th:remove="tag"></div>
  </section>

  <footer><p>yay</p></footer>
</body>
</html>
```

And then a view in the "views" subdirectory of the "WEB-INF/templates":

```html
<!-- foo.html -->
<html>
<body>
  <!-- Note the name of the fragment matches the one specified in the layout -->
  <div id="viewBody" th:fragment="content">
    <p>View body</p>
  </div>
</body>
</html>
```

Finally, implement a method that returns the layout and view name:

```java
@Layout("layout")
@RequestMapping("/foo")
public String foo() {
  return "foo";
}
```

Or, you can ignore the annotation and rely on the defaults that are set on
the interceptor:

```java
@RequestMapping("/foo")
public ModelAndView foo() {
  ModelAndView mav = new ModelAndView();
  mav.setViewName("foo");

  // do some stuff

  return mav;
}
```

In either case, the rendered HTML would be:

```html
<html>
<head></head>
<body>
  <header><h1>Example Layout</h1></header>

  <section id="viewContainer">
    <div id="viewBody">
      <p>View body</p>
    </div>
  </section>

  <footer><p>yay</p></footer>
</body>
</html>
```