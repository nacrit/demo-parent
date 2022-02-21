命令模式：
    将一个请求封装为一个对象，从而使你可用不同的请求对客户进行参数化；对请求排队或记录请求日志，以及支持可撤消的操作。

    命令模式组成：
        1) 命令角色（Command）：声明执行操作的接口。有 java 接口或者抽象类来实现。
        2) 具体命令角色（Concrete Command）：将一个接收者对象绑定于一个动作；调用接收者相应的操作，以实现命令角色声明的执行操作的接口。
        3) 客户角色（Client）：创建一个具体命令对象（并可以设定它的接收者）。
        4) 请求者角色（Invoker）：调用命令对象执行这个请求。
        5) 接收者角色（Receiver）：知道如何实施与执行一个请求相关的操作。任何类都可能作为一个接收者。

    三、举例
    本来想接着我的 JUnit 分析来讲解命令模式。但是由于在 JUnit 中，参杂了其它的模式
    在里面，使得命令模式的特点不太明显。所以这里将以命令模式在 Web 开发中最常见的应
    用——Struts 中 Action 的使用作为例子。
    在 Struts 中 Action 控制类是整个框架的核心，它连接着页面请求和后台业务逻辑处理。
    按照框架设计，每一个继承自 Action 的子类，都实现 execute 方法——调用后台真正处理业
    务的对象来完成任务。
    注：继承自 DispatchAction 的子类，则可以一个类里面处理多个类似的操作。这个在这不做讨论。
    下面我们将 Struts 中的各个类与命令模式中的角色对号入座。
    先来看下命令角色——Action 控制类
    public class Action {
     ……
     /*
     *可以看出，Action 中提供了两个版本的执行接口，而且实现了默认的空实现。
     */
    public ActionForward execute( ActionMapping mapping,
     ActionForm form,
     ServletRequest request,
     ServletResponse response)
     throws Exception {
     try {
     return execute(mapping, form, (HttpServletRequest) request,
     (HttpServletResponse) response);
     } catch (ClassCastException e) {
     return null;
     }
     }
    public ActionForward execute( ActionMapping mapping,
     ActionForm form,
     HttpServletRequest request,
     HttpServletResponse response)
     throws Exception {
     return null;
     }
    }
    下面的就是请求者角色，它仅仅负责调用命令角色执行操作。
    public class RequestProcessor {
    ……
    protected ActionForward processActionPerform(HttpServletRequest request,
     HttpServletResponse response,
     Action action,
     ActionForm form,
     ActionMapping mapping)
     throws IOException, ServletException {
     try {
     return (action.execute(mapping, form, request, response));
     } catch (Exception e) {
     return (processException(request, response,e, form, mapping));
     }
    }
    }
    Struts 框架为我们提供了以上两个角色，要使用 struts 框架完成自己的业务逻辑，剩下
    的三个角色就要由我们自己来实现了。步骤如下：
    1) 很明显我们要先实现一个 Action 的子类，并重写 execute 方法。在此方法中调用
    业务模块的相应对象来完成任务。
    2) 实现处理业务的业务类，来充当接收者角色。
    3) 配置 struts-config.xml 配置文件，将自己的 Action 和 Form 以及相应页面结合起来。
    4) 编写 jsp，在页面中显式的制定对应的处理 Action。
    一个完整的命令模式就介绍完了。当你在页面上提交请求后，Struts 框架会根据配置文
    件中的定义，将你的 Action 对象作为参数传递给 RequestProcessor 类中的
    processActionPerform()方法，由此方法调用 Action 对象中的执行方法，进而调用业务层中
    的接收角色。这样就完成了请求的处理。
