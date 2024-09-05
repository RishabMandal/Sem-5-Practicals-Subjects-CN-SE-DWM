
import java.util.ArrayList;
import java.util.List;

public class kmeanstry {
    public static void main(String[] args) {
        // Sample data points (2D coordinates)
        List<Point> dataPoints = new ArrayList<>();
        dataPoints.add(new Point(1.0, 2.0));
        dataPoints.add(new Point(2.0, 3.0));
        dataPoints.add(new Point(3.0, 4.0));
        dataPoints.add(new Point(10.0, 12.0));
        dataPoints.add(new Point(11.0, 13.0));
        dataPoints.add(new Point(12.0, 14.0));

        int k = 2; // Number of clusters

        // Run K-Means clustering
        List<Cluster> clusters = kMeans(dataPoints, k);

        // Print the results
        for (int i = 0; i < k; i++) {
            System.out.println("Cluster " + (i + 1) + ": " + clusters.get(i).getPoints());
        }
    }

    public static List<Cluster> kMeans(List<Point> dataPoints, int k) {
        List<Cluster> clusters = new ArrayList<>();

        // Initialize clusters with random data points
        for (int i = 0; i < k; i++) {
            clusters.add(new Cluster(dataPoints.get(i)));
        }

        // Perform K-Means iterations
        int maxIterations = 100;
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            // Assign each data point to the nearest cluster
            for (Point point : dataPoints) {
                Cluster nearestCluster = findNearestCluster(point, clusters);
                nearestCluster.addPoint(point);
            }

            // Update cluster centroids
            for (Cluster cluster : clusters) {
                cluster.updateCentroid();
            }
        }

        return clusters;
    }

    public static Cluster findNearestCluster(Point point, List<Cluster> clusters) {
        Cluster nearestCluster = clusters.get(0);
        double minDistance = point.distanceTo(nearestCluster.getCentroid());

        for (int i = 1; i < clusters.size(); i++) {
            Cluster currentCluster = clusters.get(i);
            double distance = point.distanceTo(currentCluster.getCentroid());

            if (distance < minDistance) {
                nearestCluster = currentCluster;
                minDistance = distance;
            }
        }

        return nearestCluster;
    }

    public static class Point {
        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double distanceTo(Point other) {
            double dx = this.x - other.x;
            double dy = this.y - other.y;
            return Math.sqrt(dx * dx + dy * dy);
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public static class Cluster {
        private List<Point> points;
        private Point centroid;

        public Cluster(Point initialPoint) {
            this.points = new ArrayList<>();
            this.centroid = new Point(initialPoint.getX(), initialPoint.getY());
        }

        public List<Point> getPoints() {
            return points;
        }

        public Point getCentroid() {
            return centroid;
        }

        public void addPoint(Point point) {
            points.add(point);
        }

        public void updateCentroid() {
            double totalX = 0;
            double totalY = 0;

            for (Point point : points) {
                totalX += point.getX();
                totalY += point.getY();
            }

            if (!points.isEmpty()) {
                centroid = new Point(totalX / points.size(), totalY / points.size());
            }
        }
    }
}
