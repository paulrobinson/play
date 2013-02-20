package org.my.misc;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author paul.robinson@redhat.com, 2012-03-06
 */
public class KeyReplacerTest {

    @Test
    public void getKey()
    {
        String key = KeyReplacer.getKey("abc${def}ghi");
        Assert.assertEquals("def", key);
    }

    @Test
    public void getKeyShort()
    {
        String key = KeyReplacer.getKey("${def}");
        Assert.assertEquals("def", key);
    }

    @Test
    public void noKey()
    {
        String key = KeyReplacer.getKey("abcdefghi");
        Assert.assertNull(key);
    }

    @Test
    public void noKeyWithBrace()
    {
        String key = KeyReplacer.getKey("abcdef}ghi");
        Assert.assertNull(key);
    }
    @Test
    public void noKeyWithDollarBrace()
    {
        String key = KeyReplacer.getKey("abc${defghi");
        Assert.assertNull(key);
    }


    @Test
    public void variableReplace()
    {
        String replaced = KeyReplacer.replaceKey("abc${def}ghi", "DEF");
        Assert.assertEquals("abcDEFghi", replaced);
    }


    @Test
    public void variableReplaceShort()
    {
        String replaced = KeyReplacer.replaceKey("${def}", "DEF");
        Assert.assertEquals("DEF", replaced);
    }
}
