package com.anjubao.fileupload.util;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathUtils {

    /**
     * uploadfiles
     */
    private static final String UPLOAD_FILE = "uploadfiles";

    /**
     * img-store前缀
     */
    private static final String IMG_STORE = "img-store";

    /**
     * 逗号分隔符
     */
    public static final String SEPARATOR_COMMA = ",";

    /**
     * 分号分隔符
     */
    public static final String SEPARATOR_SEMICOLON = ";";

    private PathUtils( ) {

    }

    /**
     * 有多个前缀默认取第一个，无前缀取默认值
     * 
     * @param prefixUrl
     * @return
     */
    public static String getFirstOrDefaultPrefixUrl(String... prefixUrl) {
        if (prefixUrl == null || prefixUrl.length == 0) {
//            return GlobalConfig.getImagePrefixUrl();
        }
        return prefixUrl[0];
    }

    /**
     * 给对应的路径加上前缀
     * 
     * @param pathWithoutPrefixUrl
     * @param prefixUrl
     * @return
     */
    public static String addPathPrefixUrl(String pathWithoutPrefixUrl, String prefixUrl) {
        String pathWithPrefixUrl = ""; // 无前缀的url地址
        if (StringUtils.isNotBlank(pathWithoutPrefixUrl)) {
//            return (StringUtils.isBlank(prefixUrl) ? GlobalConfig.getImagePrefixUrl() : prefixUrl)
//                    + pathWithoutPrefixUrl;
        }
        return pathWithPrefixUrl;
    }

    /**
     * 巡检图片前缀
     * 
     * @param pathWithoutPrefixUrl
     * @param prefixUrl
     * @return
     */
    public static String addAdCheckPathPrefixUrl(String pathWithoutPrefixUrl, String prefixUrl) {
        String pathWithPrefixUrl = ""; // 无前缀的url地址
        if (StringUtils.isNotBlank(pathWithoutPrefixUrl)) {
//            return (StringUtils.isBlank(prefixUrl) ? GlobalConfig.getAdCheckImagePrefixUrl()
//                    : prefixUrl) + pathWithoutPrefixUrl;
        }
        return pathWithPrefixUrl;
    }

    /**
     * 是否有前缀
     * 
     * @param paths
     * @return
     */
    public static boolean isHasPrefix(String paths) {
        if (StringUtils.isNotBlank(paths)) {
            return paths.indexOf(UPLOAD_FILE) > -1 || paths.indexOf(IMG_STORE) > -1;
        }
        return false;
    }

    /**
     * 从多张图或一个图片中获取一张图片，同时接上访问图片的前辍
     * 
     * @param paths
     * @return
     */
    public static String splitOneImage(String paths, String... prefixUrl) {
        String pathsWithPrefix = "";
        if (StringUtils.isNotBlank(paths)) {
            String[] pathsArrayWithoutPrefix = paths.split(SEPARATOR_COMMA);
            if (pathsArrayWithoutPrefix.length > 0) {
                if (isHasPrefix(pathsArrayWithoutPrefix[0])) {
                    pathsWithPrefix = pathsArrayWithoutPrefix[0];
                } else {
                    pathsWithPrefix =
                            addPathPrefixUrl(pathsArrayWithoutPrefix[0],
                                    getFirstOrDefaultPrefixUrl(prefixUrl));
                }
            }

        }
        return pathsWithPrefix;
    }

//    /**
//     * 巡检图片前缀
//     *
//     * @param paths
//     * @param prefixUrl
//     * @return
//     */
//    public static String splitAdCheckOneImage(String paths, String... prefixUrl) {
//        String pathsWithPrefix = "";
//        if (StringUtils.isNotBlank(paths)) {
//            String[] pathsArrayWithoutPrefix = paths.split(SEPARATOR_COMMA);
//            if (pathsArrayWithoutPrefix.length > 0) {
//                pathsWithPrefix =
//                        GlobalConfig.getAdCheckImagePrefixUrl() + pathsArrayWithoutPrefix[0];
//            }
//        }
//        return pathsWithPrefix;
//    }

    /**
     * 无前缀的路径集装换成有前缀的路径列表List
     * 
     * @param pathsWithoutPrefix
     * @return
     */
    public static List<String> pathsTransformToList(String pathsWithoutPrefix, String... prefixUrl) {
        if (StringUtils.isNotBlank(pathsWithoutPrefix)) {
            List<String> pathsListWithPrefix = new ArrayList<String>();
            String[] pathWithoutPrefixArray = pathsWithoutPrefix.split(SEPARATOR_COMMA);
            for (int i = 0; i < pathWithoutPrefixArray.length; i++) {
                pathsListWithPrefix.add(addPathPrefixUrl(pathWithoutPrefixArray[i],
                        getFirstOrDefaultPrefixUrl(prefixUrl)));
            }
            return pathsListWithPrefix;
        }
        return Collections.emptyList();
    }

//    /**
//     * 巡检图片前缀－－－－的路径集装换成有前缀的路径列表List
//     *
//     * @param pathsWithoutPrefix
//     * @return
//     */
//    public static List<String> pathsTransformAdCheckToList(String pathsWithoutPrefix,
//            String... prefixUrl) {
//        if (StringUtils.isNotBlank(pathsWithoutPrefix)) {
//            List<String> pathsListWithPrefix = new ArrayList<String>();
//            String[] pathWithoutPrefixArray = pathsWithoutPrefix.split(SEPARATOR_COMMA);
//            for (int i = 0; i < pathWithoutPrefixArray.length; i++) {
//                pathsListWithPrefix.add(addAdCheckPathPrefixUrl(pathWithoutPrefixArray[i],
//                        GlobalConfig.getAdCheckImagePrefixUrl()));
//            }
//            return pathsListWithPrefix;
//        }
//        return Collections.emptyList();
//    }

    /**
     * String数组转换为List
     * 
     * @param str
     * @return
     */
    public static List<String> stringToList(String str) {
        if (StringUtils.isNotBlank(str)) {
            List<String> tempList = new ArrayList<String>();
            String[] tempArray = str.split(SEPARATOR_COMMA);
            for (int i = 0; i < tempArray.length; i++) {
                tempList.add(tempArray[i]);
            }
            return tempList;
        }
        return Collections.emptyList();
    }

    /**
     * 多张String类型图片，逗号分开的，加前缀
     * 
     * @param pathsWithoutPrefix
     * @return
     */
    public static String addPathsPrefix(String pathsWithoutPrefix, String... prefixUrl) {
        StringBuilder pathsWithPrefix = new StringBuilder();
        if (StringUtils.isNotBlank(pathsWithoutPrefix)) {
            String[] pathsArrayWithoutPrefix = pathsWithoutPrefix.split(SEPARATOR_COMMA);
            for (int i = 0; i < pathsArrayWithoutPrefix.length; i++) {
                String pathWithPrefix =
                        addPathPrefixUrl(pathsArrayWithoutPrefix[i],
                                getFirstOrDefaultPrefixUrl(prefixUrl));
                if (i < pathsArrayWithoutPrefix.length - 1) {
                    pathsWithPrefix.append(pathWithPrefix);
                    pathsWithPrefix.append(SEPARATOR_COMMA);
                } else {
                    pathsWithPrefix.append(pathWithPrefix);
                }
            }
        }
        return pathsWithPrefix.toString();
    }

    /**
     * 验证图片前缀是否正确
     * 
     * @param pathWithPrefix
     * @return
     */
    public static void validPrefixPath(String pathWithPrefix, String... prefixUrl) {
        if (StringUtils.isNotBlank(pathWithPrefix)
                && !pathWithPrefix.startsWith(getFirstOrDefaultPrefixUrl(prefixUrl))) {
//            throw new CustomException(ErrorNumConstants.PARAMS_ERROR, "图片地址前缀不符合要求");
        }
    }

    /**
     * 移除图片地址组的前缀
     * 
     * @param pathsWithPrefix
     * @return
     */
    public static String removePathsPrefixUrl(String[] pathsWithPrefix, String... prefixUrl) {
        StringBuilder pathsWithoutPrefix = new StringBuilder();
        if (pathsWithPrefix != null && pathsWithPrefix.length > 0) {
            for (int i = 0; i < pathsWithPrefix.length; i++) {
                String path = pathsWithPrefix[i];
                path = removePathPrefixUrl(path, prefixUrl);
                if (i < pathsWithPrefix.length - 1) {
                    pathsWithoutPrefix.append(path);
                    pathsWithoutPrefix.append(SEPARATOR_COMMA);
                } else {
                    pathsWithoutPrefix.append(path);
                }
            }
        }
        return pathsWithoutPrefix.toString();
    }

    /**
     * 移除图片前缀
     * 
     * @param
     * @return
     */
    public static String removePathPrefixUrl(String pathWithPrefixUrl, String... prefixUrl) {
        String pathWithoutPrefixUrl = ""; // 无前缀的url地址
        if (StringUtils.isNotBlank(pathWithPrefixUrl)) {
            pathWithoutPrefixUrl =
                    pathWithPrefixUrl.replace(getFirstOrDefaultPrefixUrl(prefixUrl), "");
            if (pathWithoutPrefixUrl.startsWith("/")) {
                pathWithoutPrefixUrl = pathWithoutPrefixUrl.substring(1);
            }
            return pathWithoutPrefixUrl;
        }
        return pathWithoutPrefixUrl;
    }

    /**
     * 移除图片地址集合的前缀
     *
     * @param imagesList
     * @param prefixUrl
     * @return
     */
    public static String pathListRemovePathPrefixUrl(List<String> imagesList, String... prefixUrl) {
        StringBuilder pathImagesListString = new StringBuilder();
        if (imagesList != null && !imagesList.isEmpty()) {
            for (String image : imagesList) {
                String path = removePathPrefixUrl(image, prefixUrl);
                if (imagesList.get(imagesList.size() - 1).equals(image)) {
                    pathImagesListString.append(path);
                } else {
                    pathImagesListString.append(path);
                    pathImagesListString.append(SEPARATOR_SEMICOLON);
                }
            }
        }
        return pathImagesListString.toString();
    }

//    /**
//     * 统一加上图片前缀
//     *
//     * @param pathsWithoutPrefix
//     * @param prefixUrl
//     * @return
//     */
//    public static List<String> pathListStringAddPathPrefixUrl(String pathsWithoutPrefix,
//            String... prefixUrl) {
//        if (StringUtils.isNotBlank(pathsWithoutPrefix)) {
//            List<String> pathsListWithPrefix = new ArrayList<String>();
//            String[] pathWithoutPrefixArray = pathsWithoutPrefix.split(SEPARATOR_SEMICOLON);
//            for (int i = 0; i < pathWithoutPrefixArray.length; i++) {
//                pathsListWithPrefix.add(addAdCheckPathPrefixUrl(pathWithoutPrefixArray[i],
//                        GlobalConfig.getImagePrefixUrl()));
//            }
//            return pathsListWithPrefix;
//        }
//        return Collections.emptyList();
//    }

}
