import java.util.Scanner;

class Bayes {
    public static void main(String args[]) {
        float probYes = 0, probNo = 0;
        float sunnyYes = 0, sunnyNo = 0;
        float overcastYes = 0, overcastNo = 0;
        float rainyYes = 0, rainyNo = 0;
        float hotYes = 0, hotNo = 0;
        float mildYes = 0, mildNo = 0;
        float coolYes = 0, coolNo = 0;
        float highYes = 0, highNo = 0;
        float normalYes = 0, normalNo = 0;
        float trueYes = 0, trueNo = 0;
        float falseYes = 0, falseNo = 0;
        String[][] data = {
                { "Sunny", "Hot", "High", "False", "No" },
                { "Sunny", "Hot", "High", "True", "No" },
                { "Overcast", "Hot", "High", "False", "Yes" },
                { "Rain", "Mild", "High", "False", "Yes" },
                { "Rain", "Cool", "Normal", "False", "Yes" },
                { "Rain", "Cool", "Normal", "True", "No" },
                { "Overcast", "Cool", "Normal", "True", "Yes" },
                { "Sunny", "Mild", "High", "False", "No" },
                { "Sunny", "Cool", "Normal", "False", "Yes" },
                { "Rain", "Mild", "Normal", "False", "Yes" },
                { "Sunny", "Mild", "Normal", "True", "Yes" },
                { "Overcast", "Mild", "High", "True", "Yes" },
                { "Overcast", "Hot", "Normal", "False", "Yes" },
                { "Rain", "Mild", "High", "True", "No" }
        };
        for (int i = 0; i < data.length; i++) {
            if (data[i][4].equals("Yes")) {
                probYes++;
                if (data[i][0].equals("Sunny"))
                    sunnyYes++;
                if (data[i][0].equals("Overcast"))
                    overcastYes++;
                if (data[i][0].equals("Rain"))
                    rainyYes++;
                if (data[i][1].equals("Hot"))
                    hotYes++;
                if (data[i][1].equals("Mild"))
                    mildYes++;
                if (data[i][1].equals("Cool"))
                    coolYes++;
                if (data[i][2].equals("High"))
                    highYes++;
                if (data[i][2].equals("Normal"))
                    normalYes++;
                if (data[i][3].equals("True"))
                    trueYes++;
                if (data[i][3].equals("False"))
                    falseYes++;
            } else {
                probNo++;
                if (data[i][0].equals("Sunny"))
                    sunnyNo++;
                if (data[i][0].equals("Overcast"))
                    overcastNo++;
                if (data[i][0].equals("Rain"))
                    rainyNo++;
                if (data[i][1].equals("Hot"))
                    hotNo++;
                if (data[i][1].equals("Mild"))
                    mildNo++;
                if (data[i][1].equals("Cool"))
                    coolNo++;
                if (data[i][2].equals("High"))
                    highNo++;
                if (data[i][2].equals("Normal"))
                    normalNo++;
                if (data[i][3].equals("True"))
                    trueNo++;
                if (data[i][3].equals("False"))
                    falseNo++;
            }
        }
        sunnyYes /= probYes;
        sunnyNo /= probNo;
        overcastYes /= probYes;
        overcastNo /= probNo;
        rainyYes /= probYes;
        rainyNo /= probNo;
        hotYes /= probYes;
        hotNo /= probNo;
        mildYes /= probYes;
        mildNo /= probNo;
        coolYes /= probYes;
        coolNo /= probNo;
        highYes /= probYes;
        highNo /= probNo;
        normalYes /= probYes;
        normalNo /= probNo;
        trueYes /= probYes;
        trueNo /= probNo;
        falseYes /= probYes;
        falseNo /= probNo;
        probYes /= data.length;
        probNo /= data.length;

        // Ask input from user
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter values for Outlook, Temperature, Humidity, Windy:");
        String[] unseenTuple = new String[4];
        for (int i = 0; i < 4; i++) {
            unseenTuple[i] = sc.next();
        }
        double probYesUnseen = probYes * ((unseenTuple[0].equals("Sunny") ? sunnyYes
                : (unseenTuple[0].equals("Overcast") ? overcastYes : rainyYes))
                * (unseenTuple[1].equals("Hot") ? hotYes : unseenTuple[1].equals("Mild") ? mildYes : coolYes) *
                (unseenTuple[2].equals("High") ? highYes : normalYes) *
                (unseenTuple[3].equals("True") ? trueYes : falseYes));
        double probNoUnseen = probNo *
                ((unseenTuple[0].equals("Sunny") ? sunnyNo
                        : (unseenTuple[0].equals("Overcast") ? overcastNo : rainyNo)) *
                        (unseenTuple[1].equals("Hot") ? hotNo : (unseenTuple[1].equals("Mild") ? mildNo : coolNo)) *
                        (unseenTuple[2].equals("High") ? highNo : normalNo) *
                        (unseenTuple[3].equals("True") ? trueNo : falseNo));
        String predictedClass = (probYesUnseen > probNoUnseen) ? "Yes" : "No";
        System.out.println("Probability of YES: " + probYesUnseen + " Probability of NO: " + probNoUnseen);
        System.out.println("Predicted Class: " + predictedClass);
        sc.close();
    }
}