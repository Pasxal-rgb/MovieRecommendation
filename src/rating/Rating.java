package rating;


    public enum Rating {
        NOT_RATED(-1),
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5);


        private final int value;

        Rating(int value) {
            this.value = value;
        }


        public int getValue() {
            return this.value;
        }

        public boolean isRated(){
            return this != NOT_RATED;
        }
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