public interface EventInterface {
    default int drawEvent(int numberOfEvents) {
        return 0;
    }
}
