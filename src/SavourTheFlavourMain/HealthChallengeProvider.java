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
            "Avoid fried food for the whole day",
            "Swap a soft drink for a glass of lemon water today",
            "Do 20 squats every hour for the next 3 hours",
            "Eat a protein-rich breakfast today",
            "Meditate or sit quietly for 10 minutes today",
            "Sleep at least 7 hours tonight — no excuses",
            "Stretch your body for 5 minutes after every 2 hours of sitting",
            "Take the stairs instead of the elevator all day",
            "No junk food for the entire day",
            "Write down 3 things you're grateful for today",
            "Stand or walk while talking on the phone today",
            "Pack your own healthy lunch or snack",
            "Replace white rice or bread with brown alternatives for one meal",
            "Do 10 push-ups or jumping jacks every time you check your phone unnecessarily",
            "Spend 10 minutes outdoors—get some sunlight and fresh air",
            "Add one extra vegetable to every meal today"
    );

    public static void showRandomChallenge() {
        Random random = new Random();
        String challenge = challenges.get(random.nextInt(challenges.size()));
        System.out.println("\n Today's Health Challenge:");
        System.out.println(" " + challenge);
    }
}

