package com.randyriegsecker.statescapitalsquiz;

// Author: Randy Riegsecker
// Date: 2022/12/01
// States & Capitals Quiz quiz questions
// https://github.com/randy-riegsecker/StatesCapitalsQuiz

// This is your question list and quiz setup.  There's no error checking so make sure it's accurate.
// The number of questions can be anything you want.  The example test has 50 questions with 5 options each.
// If you want a 4 option question, references to the 5th answer and button would need to be removed.

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class Questions {

    public String questionList[][] = {
            // Format is as follows for a quiz five possible answers:
            // Question, Correct Answer, Wrong Answer 1, Wrong Answer 2, Wrong Answer 3, Wrong Answer 4
            {"What is the capital of Alabama?", "Montgomery", "Birmingham", "Huntsville", "Mobile", "Tuscaloosa"},
            {"What is the capital of Alaska?", "Juneau", "Anchorage", "Fairbanks", "Wasilla", "Valdez"},
            {"What is the capital of Arizona?", "Phoenix", "Tucson", "Mesa", "Sedona", "Scottsdale"},
            {"What is the capital of Arkansas?", "Little Rock", "Fayetteville", "Fort Smith", "Springdale", "Jonesboro"},
            {"What is the capital of California?", "Sacramento", "Los Angeles", "San Diego", "San Francisco", "Fresno"},
            {"What is the capital of Colorado?", "Denver", "Colorado Springs", "Aurora", "Boulder", "Grand Junction"},
            {"What is the capital of Connecticut?", "Hartford", "Bridgeport", "Stamford", "New Haven", "Waterbury"},
            {"What is the capital of Delaware?", "Dover", "Wilmington", "Newark", "Middletown", "Smyrna"},
            {"What is the capital of Florida?", "Tallahassee", "Jacksonville", "Miami", "Tampa", "Pensacola"},
            {"What is the capital of Georgia?", "Atlanta", "Columbus", "Savannah", "Macon", "Augusta"},
            {"What is the capital of Hawaii?", "Honolulu", "Oahu", "Pearl City", "Hilo", "Kailua"},
            {"What is the capital of Idaho?", "Boise", "Meridian", "Nampa", "Idaho Falls", "Caldwell"},
            {"What is the capital of Illinois?", "Springfield", "Chicago", "Aurora", "Joliet", "Rockford"},
            {"What is the capital of Indiana?", "Indianapolis", "Fort Wayne", "Muncie", "South Bend", "Evansville"},
            {"What is the capital of Iowa?", "Des Moines", "Cedar Rapids", "Davenport", "Sioux City", "Iowa City"},
            {"What is the capital of Kansas?", "Topeka", "Wichita", "Overland Park", "Kansas City", "Olathe"},
            {"What is the capital of Kentucky?", "Frankfort", "Louisville", "Lexington", "Bowling Green", "Owensboro"},
            {"What is the capital of Louisiana?", "Baton Rouge", "New Orleans", "Shreveport", "Lafayette", "Lake Charles"},
            {"What is the capital of Maine?", "Augusta", "Portland", "Lewiston", "Bangor", "Auburn"},
            {"What is the capital of Maryland?", "Annapolis", "Baltimore", "Columbia", "Germantown", "Silver Spring"},
            {"What is the capital of Massachusetts?", "Boston", "Cambridge", "Plymouth", "Springfield", "Salem"},
            {"What is the capital of Michigan?", "Lansing", "Detroit", "Grand Rapids", "Ann Arbor", "Traverse City"},
            {"What is the capital of Minnesota?", "Saint Paul", "Minneapolis", "Rochester", "Duluth", "Bloomington"},
            {"What is the capital of Mississippi?", "Jackson", "Gulfport", "Southaven", "Biloxi", "Hattiesburg"},
            {"What is the capital of Missouri?", "Jefferson City", "Kansas City", "Saint Louis", "Springfield", "Independence"},
            {"What is the capital of Montana?", "Helena", "Billings", "Missoula", "Bozeman", "Butte"},
            {"What is the capital of Nebraska?", "Lincoln", "Omaha", "Bellevue", "Grand Island", "Kearney"},
            {"What is the capital of Nevada?", "Carson City", "Las Vegas", "Henderson", "Reno", "Paradise"},
            {"What is the capital of New Hampshire?", "Concord", "Nashua", "Manchester", "Dover", "Rochester"},
            {"What is the capital of New Jersey?", "Trenton", "Newark", "Jersey City", "Paterson", "Edison"},
            {"What is the capital of New Mexico?", "Santa Fe", "Albuquerque", "Las Cruces", "Rio Rancho", "Roswell"},
            {"What is the capital of New York?", "Albany", "New York City", "Buffalo", "Rochester", "Syracuse"},
            {"What is the capital of North Carolina?", "Raleigh", "Charlotte", "Greensboro", "Durham", "Winston-Salem"},
            {"What is the capital of North Dakota?", "Bismarck", "Fargo", "Grand Forks", "Minot", "West Fargo"},
            {"What is the capital of Ohio?", "Columbus", "Cleveland", "Cincinnati", "Akron", "Dayton"},
            {"What is the capital of Oklahoma?", "Oklahoma City", "Tulsa", "Norman", "Broken Arrow", "Edmond"},
            {"What is the capital of Oregon?", "Salem", "Portland", "Eugene", "Gresham", "Hillsboro"},
            {"What is the capital of Pennsylvania?", "Harrisburg", "Philadelphia", "Pittsburgh", "Allentown", "Erie"},
            {"What is the capital of Rhode Island?", "Providence", "Pawtucket", "Warwick", "East Providence", "Cranston"},
            {"What is the capital of South Carolina?", "Columbia", "Charleston", "Mount Pleasant", "Rock Hill", "Myrtle Beach"},
            {"What is the capital of South Dakota?", "Pierre", "Sioux Falls", "Rapid City", "Aberdeen", "Brookings"},
            {"What is the capital of Tennessee?", "Nashville", "Memphis", "Knoxville", "Chattanooga", "Clarksville"},
            {"What is the capital of Texas?", "Austin", "Houston", "San Antonio", "Dallas", "Fort Worth"},
            {"What is the capital of Utah?", "Salt Lake City", "Provo", "West Jordan", "Orem", "West Valley City"},
            {"What is the capital of Vermont?", "Montpelier", "Burlington", "South Burlington", "Rutland", "Hartford"},
            {"What is the capital of Virginia?", "Richmond", "Arlington", "Norfolk", "Chesapeake", "Virginia Beach"},
            {"What is the capital of Washington?", "Olympia", "Seattle", "Spokane", "Tacoma", "Vancouver"},
            {"What is the capital of West Virginia?", "Charleston", "Huntington", "Morgantown", "Parkersburg", "Wheeling"},
            {"What is the capital of Wisconsin?", "Madison", "Milwaukee", "Green Bay", "Kenosha", "Racine"},
            {"What is the capital of Wyoming?", "Cheyenne", "Casper", "Laramie", "Gillette", "Rock Springs"}
    };

    // returns the number of questions (rows) in the array
    private int numQuestions = questionList.length;
    // returns the number of columns in the array.  Could be used to flexibly change to 4 answers.
    private int numCols = questionList[0].length;

    // Array to store the correct answers before we randomize them
    ArrayList<String> correctAnswers = new ArrayList<String>();

    public Questions() {

        // Fill up array of correct answers
        fillCorrectAnswers();

        // Randomize the answer order so it different every time
        randomizeAnswerOrder();
    }

    public int getNumQuestions() {
        return numQuestions;
    }

    public int getNumCols() {
        return numCols;
    }

    public String getAnswerA(int questionNum) {
        return questionList[questionNum][1];
    }

    public String getAnswerB(int questionNum) {
        return questionList[questionNum][2];
    }

    public String getAnswerC(int questionNum) {
        return questionList[questionNum][3];
    }

    public String getAnswerD(int questionNum) {
        return questionList[questionNum][4];
    }

    public String getAnswerE(int questionNum) {
        return questionList[questionNum][5];
    }

    public String getQuestion(int questionNum) {
        return questionList[questionNum][0];
    }

    public String getCorrectAnswer(int questionNum) {
        // System.out.println("Correct answer is for questionNum " + questionNum + " is " + correctAnswers.get(questionNum));
        return correctAnswers.get(questionNum);
    }

    // Save the correct answers to a separate array before we rearrange them for the test.
    public void fillCorrectAnswers() {
        for (int index = 0; index < numQuestions; index++) {
            correctAnswers.add(questionList[index][1]);
        }
    }

    public void randomizeAnswerOrder() {

        int answerIndex; // Temporary usage

        // Use the Fisher-Yates shuffle model to shuffle answers
        // for example, with each question array quiz there are 6 columns
        // we want to randomize numCols-1, or 5 columns that contain the answers
        int numberOfPossibleAnswers = numCols - 1;

        // SecureRandom better than Random
        Random random = new SecureRandom();

        // Loop through every question to randomize the answer order
        for (int questionIndex = 0; questionIndex < questionList.length; ++questionIndex) {

            // This is a bit of spaghetti code to make a variant of Fisher-Yates algorithm, which
            // is the best algorithm to shuffle an array with correctly with randomness.
            // It's also the algorithm in the Randomizer Class
            // See https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
            //
            // In our string array, for a given question number [questionIndex],
            // questionList[questionIndex][0] is the question, so it's always excluded.
            // questionList[questionIndex][1] is always the correct answer in the array.
            // We're randomizing array elements questionList[questionIndex][1] through
            // questionList[questionIndex][5] to rearrange the order of answers displayed for the quiz.

            for (answerIndex = 0; answerIndex < numberOfPossibleAnswers; answerIndex++) {

                int randomValue = answerIndex + random.nextInt(numberOfPossibleAnswers - answerIndex);

                // Now swap the current element answerIndex with the randomElement
                String randomElement = questionList[questionIndex][randomValue + 1];
                questionList[questionIndex][randomValue + 1] = questionList[questionIndex][answerIndex + 1];
                questionList[questionIndex][answerIndex + 1] = randomElement;
            }
        }
    }
}