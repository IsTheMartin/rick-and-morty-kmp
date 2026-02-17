echo "Running pre-commit checks..."

# KtLint check
echo "1. Running ktlintCheck"
./gradlew ktlintCheck
if [ $? -ne 0 ]; then
  echo "ktlintCheck failed. Please fix formatting issues before commit."
  exit 1
fi

# Unit tests
echo "1. Running unit tests"
./gradlew :composeApp:testDebugUnitTest
if [ $? -ne 0 ]; then
  echo "Unit tests failed. Please fix test failures before commit."
  exit 1
fi

# KtLint check
echo "1. Running roborazzi verification"
./gradlew :composeApp:verifyRoborazziDebug
if [ $? -ne 0 ]; then
  echo "Roborazzi verification failed. Please check screenshots before commit."
  exit 1
fi

echo "All checks passed! Committing..."
exit 0
