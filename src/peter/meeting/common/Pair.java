package peter.meeting.common;

public final class Pair<A, B> {
    private A first;

    private B second;

    public Pair() {
    }

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public static <T, U> Pair<T, U> create(T t, U u) {
        return new Pair<T, U>(t, u);
    }

    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Pair<?, ?> pair = (Pair<?, ?>) o;

        if (first != null ? !first.equals(pair.first) : pair.first != null) {
            return false;
        }
        // noinspection RedundantIfStatement
        if (second != null ? !second.equals(pair.second) : pair.second != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = (first != null ? first.hashCode() : 0);
        result = 35 * result + (second != null ? second.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pair(" + first + ", " + second + ")";
    }
}
