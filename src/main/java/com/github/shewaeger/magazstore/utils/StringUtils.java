package com.github.shewaeger.magazstore.utils;

import com.github.shewaeger.magazstore.exceptions.ServiceException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private final static long KB_FACTOR = 1024;
    private final static long MB_FACTOR = 1024 * KB_FACTOR;
    private final static long GB_FACTOR = 1024 * MB_FACTOR;
    private final static Pattern sizeMather = Pattern.compile("^\\s*(?<size>\\d+)\\s*(?<factor>(GB|MB|KB)?)$", Pattern.CASE_INSENSITIVE);

    public static long parseSize(String size) {
        Matcher matcher = sizeMather.matcher(size);
        if (!matcher.matches())
            throw new ServiceException("Unable to read attachment size from properties");
        String sizeSNum = matcher.group("size");
        String factor = matcher.group("factor");

        Long sizeL = Long.decode(sizeSNum);

        if (factor.equalsIgnoreCase("GB"))
            return sizeL * GB_FACTOR;
        if (factor.equalsIgnoreCase("MB"))
            return sizeL * MB_FACTOR;
        if (factor.equalsIgnoreCase("KB"))
            return sizeL * KB_FACTOR;
        return sizeL;
    }

    public static boolean isNullOrEmpty(String string){
        return string == null || string.isEmpty();
    }

}
