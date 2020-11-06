package test;

/**
 * @author: YeJunyu
 * @description:
 * @email: yyyejunyu@gmail.com
 * @date: 2020/11/6
 */
public class BRequest implements Request{
    @Override
    public String getUrl() {
        return "B";
    }

    @Override
    public String getName() {
        return "B";
    }
}
