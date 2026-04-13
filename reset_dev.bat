@echo off
cd /d "c:\Users\Santiago\Desktop\Quizz\QuizSantiago-Miguel"
git checkout dev
git rm -r --cached src/main/java/org/example/quiz/controller 2>nul
git rm -r --cached src/main/java/org/example/quiz/service 2>nul
git rm -r --cached src/main/java/org/example/quiz/security 2>nul
git rm -r --cached src/main/java/org/example/quiz/config 2>nul
git rm -r --cached src/main/java/org/example/quiz/entity 2>nul
git rm -r --cached src/main/java/org/example/quiz/repository 2>nul
git rm -r --cached src/main/java/org/example/quiz/dto 2>nul
git commit -m "chore: reset dev base"
git push origin dev
pause
