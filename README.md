# LinkedIn UI Test Suite (IT3153 - Assignment 04)

Automated UI tests for LinkedIn using Java, Selenium WebDriver, TestNG and Maven, written with the Page Object Model.

## Tests
| ID | Test | Description |
|----|------|-------------|
| TC01 | LoginTest.validLogin | Valid login reaches the feed page |
| TC02 | LoginTest.invalidPassword | Wrong password shows an error message |
| TC03 | SearchTest.searchShowsResults | Search shows a results page with a visible result |
| TC04 | SearchResultTest.openFirstResult | The first result opens the matching page |

## Project structure
```
src/test/java
  base/    BaseTest (starts and closes the browser for each test)
  pages/   LoginPage, HomePage, SearchResultsPage (Page Objects)
  tests/   LoginTest, SearchTest, SearchResultTest
  utils/   Config (reads credentials), ScreenshotUtil, TestListener (screenshot on failure)
testng.xml (suite definition)
```

## Prerequisites
- JDK 17 or later
- Google Chrome (ChromeDriver is downloaded automatically by Selenium Manager)
- Maven, or IntelliJ IDEA (which includes Maven)
- Your own LinkedIn account, preferably a dedicated test account

## Configure credentials
Credentials are read from the environment variables `LI_USER` and `LI_PASS`. They are never stored in the code or the repository.

Windows (PowerShell):
```
$env:LI_USER="your_test_email"
$env:LI_PASS="your_password"
```

macOS / Linux:
```
export LI_USER=your_test_email
export LI_PASS=your_password
```

In IntelliJ IDEA: Run > Edit Configurations > select the TestNG configuration > Modify options > Environment variables, then enter `LI_USER=your_test_email;LI_PASS=your_password`.

## Run the tests
- Command line: `mvn test`
- IntelliJ IDEA: right-click `testng.xml` and choose Run

When a test fails, a screenshot is saved to the `screenshots/` folder.

## Notes and limitations
- If LinkedIn shows a CAPTCHA or verification step, the test pauses for up to 2 minutes so you can complete it manually. It is never bypassed.
- LinkedIn changes its page structure often, and many ids and class names are generated dynamically. Locators use stable attributes (input type, placeholder, visible text, href patterns) and are kept in the `pages` classes, so a broken locator only needs changing in one place.
- Repeated automated logins can trigger LinkedIn security checks, so run the suite sparingly.
- Use only your own account.