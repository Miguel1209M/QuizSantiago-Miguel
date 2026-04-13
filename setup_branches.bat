@echo off
cd /d "c:\Users\Santiago\Desktop\Quizz\QuizSantiago-Miguel"

echo ========================================
echo  SETUP DE RAMAS - QuizSantiago-Miguel
echo ========================================

REM Paso 1: commit inicial en la rama actual (main)
git add -A
git commit -m "chore: initial project setup"

REM Paso 2: crear rama dev
git checkout -b dev
echo [OK] Rama dev creada

REM Paso 3: crear feature/add-Security desde dev
git checkout -b feature/add-Security
git add -A
git commit -m "feat: add JWT Security with ADMIN and DRIVER roles"
echo [OK] feature/add-Security committed

REM Paso 4: volver a dev y crear feature/add-crud-trucks
git checkout dev
git checkout -b feature/add-crud-trucks
git add -A
git commit -m "feat: add CRUD for Trucks entity"
echo [OK] feature/add-crud-trucks committed

REM Mostrar ramas
echo.
echo === Ramas creadas ===
git branch -a

echo.
echo LISTO! Abre Git (GitKraken, SourceTree o GitHub Desktop) para confirmar.
pause
