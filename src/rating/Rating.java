package rating;


    public enum Rating {
        NOT_RATED,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE;


        @Override
        public String toString() {
            switch (this) {
                case ONE:
                    return "1";
                case TWO:
                    return "2";
                case THREE:
                    return "3";
                case FOUR:
                    return "4";
                case FIVE:
                    return "5";
                default:
                    return super.toString();
            }

        }
    }