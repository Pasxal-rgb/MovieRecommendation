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
            return switch (this) {
                case ONE -> "1";
                case TWO -> "2";
                case THREE -> "3";
                case FOUR -> "4";
                case FIVE -> "5";
                default -> super.toString();
            };

        }
    }