       IDENTIFICATION DIVISION.
       PROCEDURE DIVISION.

         EVALUATE X
           WHEN 1
             DISPLAY 1
         END-EVALUATE.

         EVALUATE X
           WHEN 1
             DISPLAY 1
           WHEN OTHER
             DISPLAY "OTHER"
         END-EVALUATE.

         EVALUATE X
           WHEN 1
             DISPLAY 1
           WHEN 2
             DISPLAY 2
         END-EVALUATE.
