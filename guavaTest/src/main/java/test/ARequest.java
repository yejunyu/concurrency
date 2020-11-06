package test;

/**
 * @author: YeJunyu
 * @description:
 * @email: yyyejunyu@gmail.com
 * @date: 2020/11/6
 */
public class ARequest implements Request{
    @Override
    public String getUrl() {
        return "A";
    }

    @Override
    public String getName() {
        return "A";
    }
}
