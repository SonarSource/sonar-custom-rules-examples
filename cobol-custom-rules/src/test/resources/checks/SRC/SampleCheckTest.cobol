       IDENTIFICATION DIVISION.                                                           
       PROGRAM-ID. ENQ200.                                                    
       ENVIRONMENT DIVISION.                                                  
       DATA DIVISION.                                                                                                                
       PROCEDURE DIVISION. *> Noncompliant {{Issue here !}}
       PARAGRAPH.                                                                                                                       
             IF ABT-IN-PROGRESS = 'N'
                     NEXT SENTENCE
             END-IF.
