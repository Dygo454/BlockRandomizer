package dygoat.blockrandomizer;

import java.util.Objects;

class Point {
    public final int x;
    public final int z;

    public Point(int x, int z) {
        this.x = x;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return x == point.x && z == point.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, z);
    }
}