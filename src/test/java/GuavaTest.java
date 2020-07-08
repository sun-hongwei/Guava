import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import java.util.List;
import java.util.Map;

public class GuavaTest {

    /**
     * 1.字符串的处理：分割，连接，填充
     */
    @Test
    public void joinerListTest() {
        List<String> lists = Lists.newArrayList("a","b","g","8","9");
        String result = Joiner.on(",").join(lists);
        System.out.println(result);
    }

    /**
     * joiner skipNulls()连接跳过null元素(第一个test为了跟第二个进行比对一下)
     * 因为List 中含有 null java.lang.NullPointerException
     */
    @Test
    public void joinerListTest1() {
        List<String> lists = Lists.newArrayList("a","b","g",null,"8","9");
        String result = Joiner.on(",").join(lists);
        System.out.println(result);
    }

    /**
     * 过滤 null
     */
    @Test
    public void joinerListTest2() {
        List<String> lists = Lists.newArrayList("a","b","g",null,"8","9");
        String result = Joiner.on(",").skipNulls().join(lists);
        System.out.println(result);
    }

    /**
     *  joiner useForNull(final String value)用value替换null元素值
     */
    @Test
    public void useNullListTest() {
        List<String> lists = Lists.newArrayList("a", "b", "g", null, "8", "9");
        String result = Joiner.on(",").useForNull("哈哈").join(lists);
        System.out.println(result);
    }

    /**
     *  joiner withKeyValueSeparator(String value)   map连接器，keyValueSeparator为key和value之间的分隔符
     */
    @Test
    public void withMapTest() {
        Map<Integer, String> maps = Maps.newHashMap();
        maps.put(1, "哈哈");
        maps.put(2, "压压");
        String result = Joiner.on(",").withKeyValueSeparator(":").join(maps);
        System.out.println(result);
        System.out.println(maps);
    }

    /**
     *  splitter on 拆分
     */
    @Test
    public void splitterListTest() {
        String test = "34344,34,34,哈哈";
        List<String> lists = Splitter.on(",").splitToList(test);
        System.out.println(lists);
    }

    /**
     *  splitter trimResults 拆分去除前后空格
     */
    @Test
    public void trimResultListTest() {
        String test = "  34344,34,34,哈哈 ";
        List<String> lists = Splitter.on(",").trimResults().splitToList(test);
        System.out.println(lists);
    }

    /**
     * splitter omitEmptyStrings 去除拆分出来空的字符串
     */
    @Test
    public void omitEmptyStringsTest() {
        String test = "  3434,434,34,,哈哈 ";
        List<String> lists = Splitter.on(",").omitEmptyStrings().splitToList(test);
        System.out.println(lists);
    }

    /**
     * splitter fixedLength(int lenght) 把字符串按固定长度分割
     */
    @Test
    public void fixedLengthTest() {
        String test = "343443434哈哈";
        List<String> lists = Splitter.fixedLength(3).splitToList(test);
        System.out.println(lists);
    }

    /**
     *  charMatcher 匹配器
     */
    @Test
    public void isTest() {
        String str = "12312,agg";
        CharMatcher charMatcher1 = CharMatcher.is('g');
        System.out.println(charMatcher1.retainFrom(str));
    }

    /**
     * charMatcher  retainFrom(String s)  在字符序列中保留匹配字符，移除其他字符
     */
    @Test
    public void charMatcherTest() {
        String str = "12312,agg  ";
        //两个匹配符,先匹配再操作
        CharMatcher charMatcher1 = CharMatcher.is('1');
        CharMatcher charMatcher2 = CharMatcher.is('2');
        //两个CharMatcher或操作
        CharMatcher charMatcher3 = charMatcher1.or(charMatcher2);
        System.out.println(charMatcher3.retainFrom(str));
    }

    /**
     * charMatcher matchersAllOf(Char char) 测试是否字符序列所有字符都匹配
     */
    @Test
    public void matchesAllOfTest1() {
        String str = "12312,agg";
        CharMatcher charMatcher1 = CharMatcher.is('g');
        System.out.println(charMatcher1.matchesAllOf(str));
    }

    @Test
    public void matchesAllOfTest2() {
        String str = "ggggg";
        CharMatcher charMatcher1 = CharMatcher.is('g');
        System.out.println(charMatcher1.matchesAllOf(str));
    }
}
