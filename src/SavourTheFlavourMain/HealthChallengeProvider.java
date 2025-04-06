package SavourTheFlavourMain;


import java.util.*;

public class HealthChallengeProvider {
    private static final List<String> challenges = List.of(
            "Drink 8 glasses of water today",
            "Take a 30-minute walk",
            "Replace one sugary snack with fruit",
            "Do 15 minutes of stretching",
            "No screens 1 hour before bed",
            "Eat at least 3 servings of vegetables",
            "Avoid fried food for the whole day"
    );

    public static void showRandomChallenge() {
        Random random = new Random();
        String challenge = challenges.get(random.nextInt(challenges.size()));
        System.out.println("\n💪 Today's Health Challenge:");
        System.out.println("👉 " + challenge);
    }
}

