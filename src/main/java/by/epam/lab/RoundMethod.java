package by.epam.lab;

public enum RoundMethod {
    FLOOR {
        @Override
        int roundFunction(double a) {
            return (int) Math.floor(a);
        }
    },
    CEIL {
        @Override
        int roundFunction(double a) {
            return (int) Math.ceil(a);
        }
    },
    ROUND {
        @Override
        int roundFunction(double a) {
            return (int) Math.round(a);
        }
    };

    abstract int roundFunction(double a);

    public int round(double roundedValue, int d) {
        int tenPow = pow(10, d);
        return roundFunction(roundedValue / tenPow) * tenPow;
    }

    private static int pow(int base, int exponent) {
        int ans = 1;
        for (int i = 0; i < exponent; i++) {
            ans *= base;
        }
        return ans;
    }
}
