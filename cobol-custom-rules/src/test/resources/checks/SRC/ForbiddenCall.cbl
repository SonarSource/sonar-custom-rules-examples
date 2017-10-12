       IDENTIFICATION DIVISION.
       PROCEDURE DIVISION.
         CALL "ALLOWED37" USING PARAM1.
         CALL "FORBIDDEN41".              *> Noncompliant {{CALL to this program are forbidden.}}
         *>   ^^^^^^^^^^^^^
         CALL "FORBIDDEN42" USING PARAM1. *> Noncompliant
         CALL UNKNOWN-PROGRAM.
         DISPLAY "FORBIDDEN42".
