package addsynth.core.util.math.number;

public final class BinaryEncoder {

  /* I was going to do this version, but it needs extra logic to prevent the user from inputting duplicates.
  public static final int encode(int ... values){
    if(values.length == 0) return 0;
    if(values.length == 1) return values[0];
    int number = values[0];
    int i;
    int length = values.length;
    for(i = 0; i < length; i++){
      number += 1 << values[i];
    }
    return number;
  }
  */

  /** Pass in true/false whether to encode that index. */
  public static final int encode(boolean ... values){
    if(values.length == 0) return 0;
    if(values.length == 1) return values[0] ? 1 : 0;
    int number = 0;
    int i;
    int length = Math.min(values.length, 32);
    for(i = 0; i < length; i++){
      if(values[i]){
        number += (1 << i);
      }
    }
    return number;
  }

  /** Returns whether the index is encoded in the value. */
  public static final boolean decode(int number, int index){
    int x = 1 << (index % 32);
    return (number & x) > 0;
  }

}
