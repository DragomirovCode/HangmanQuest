public enum PersonState {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(0);

    private final int state;

    PersonState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public static PersonState getStateByCode(int code) {
        for (PersonState state : PersonState.values()) {
            if (state.getState() == code) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid state code");
    }

    public void printMap() {
        switch (this) {
            case ONE:
                drawFourthState();
                break;
            case TWO:
                drawThirdState();
                break;
            case THREE:
                drawSecondState();
                break;
            case FOUR:
                drawFirstState();
                break;
            case FIVE:
                drawFifthState();
                break;
            default:
                System.out.println("State not supported.");
        }
    }



    public void drawFirstState() {
        System.out.println("\n                _____" +
                "\n               |     |" +
                "\n                     |" +
                "\n                     |" +
                "\n                     |" +
                "\n                 _______\n");
    }

    public void drawSecondState() {
        System.out.println("\n                _____" +
                "\n               |     |" +
                "\n               o     |" +
                "\n                     |" +
                "\n                     |" +
                "\n                 _______\n");
    }

    public void drawThirdState() {
        System.out.println("\n                _____" +
                "\n               |     |" +
                "\n               o     |" +
                "\n               |     |" +
                "\n                     |" +
                "\n                 _______\n");
    }

    public void drawFourthState() {
        System.out.println("\n                _____" +
                "\n               |     |" +
                "\n               o     |" +
                "\n               |     |" +
                "\n              /      |" +
                "\n                 _______\n");
    }

    public void drawFifthState() {
        System.out.println("\n                _____" +
                "\n               |     |" +
                "\n               o     |" +
                "\n               |     |" +
                "\n              / \\    |" +
                "\n                 _______\n");
    }
}
