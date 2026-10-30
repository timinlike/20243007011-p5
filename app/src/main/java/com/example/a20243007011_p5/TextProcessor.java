package com.example.a20243007011_p5; // 注意：如果你的包名不是 com.example.p5，请保留你自己的包名

public class TextProcessor {
    // 静态方法，用于处理和验证用户输入
    public static String processMunicipality(String municipality) {
        // 1. 检查 null 或空白
        if (municipality == null || municipality.trim().isEmpty()) {
            return null;
        }

        // 2. 忽略大小写匹配
        String normalized = municipality.trim().toLowerCase();
        switch (normalized) {
            case "beijing":
                return "Beijing";
            case "shanghai":
                return "Shanghai";
            case "tianjin":
                return "Tianjin";
            case "chongqing":
                return "Chongqing";
            default:
                return null; // 不支持的城市，返回 null
        }
    }
}