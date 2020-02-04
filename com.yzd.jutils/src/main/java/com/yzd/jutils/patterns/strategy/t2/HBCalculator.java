package com.yzd.jutils.patterns.strategy.t2;

import com.yzd.jutils.patterns.strategy.t1.ICalculator;

import java.util.Map;

public enum HBCalculator implements ICalculator {
    算法1 {
        @Override
        public Object calculate(Map<String, Object> arg) {
            return 1;
        }
    },
    算法2 {
        @Override
        public Object calculate(Map<String, Object> arg) {
            return 2;
        }
    };
}
