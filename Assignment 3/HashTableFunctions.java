//Yusuf Kathrada
//Assignment 3
//March 2022

class HashTableFunctions
{
   // hash function weights
   // 9 integers, each in the range 0-5 to act as weights for the characters in the keys
   int [] weights = {4, 2, 0, 3, 1, 0, 2, 2, 4} ;      // previous best: {0, 1, 2, 3, 3, 4, 0, 1, 2} -->  38 Collisions
   // ADD YOUR WEIGHTS INSTEAD OF 1s

   // returns True if the hash table contains string s
   // return False if the hash table does not contain string s
   boolean find ( String s, int h, int hashTableSize, String [] hashTableArray )
   {  
      for (int i = 0; i<hashTableSize; i++)
      {
         if (hashTableArray[i] == s)
         {
            return true;
         }
      }
      
      return false;
   }
   
}
