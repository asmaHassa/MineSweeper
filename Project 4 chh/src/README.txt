public void printMinefield() {//
        String output = "   ";
        for (int j = 0; j < columns; j++) {
            output+= j + "   ";
        }
        output+="\n";
        for (int i = 0; i < rows; i++) { //row
            output += i + " ";
//                if (i == rows) {
//                    output += "\n";

            for (int j = 0; j < columns; j++) { //col
                   output += j + " ";
//                    if (j == columns) {
//                        output += "\n";
                if( j > 9){
                    output+=" ";
                }
                    if (currBoard[i][j].getStatus().equals("0")) {
                        output += ANSI_YELLOW + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else if (currBoard[i][j].getStatus().equals("1")) {
                        output += ANSI_GREEN + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else if (currBoard[i][j].getStatus().equals("2")) {
                        output += ANSI_BLUE + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else  if (currBoard[i][j].getStatus().equals("3")) {
                        output += ANSI_RED + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else if (currBoard[i][j].getStatus().equals("4")) {
                        output += ANSI_RED_BRIGHT + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else if (currBoard[i][j].getStatus().equals("5")) {
                        output += CYAN + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else if (currBoard[i][j].getStatus().equals("6")) {
                        output += RED_YELLOW + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else if (currBoard[i][j].getStatus().equals("7")) {
                        output += AQUA + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else if (currBoard[i][j].getStatus().equals("8")) {
                        output += MAGENTA + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else if (currBoard[i][j].getStatus().equals("9")) {
                        output += PINK + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else if (currBoard[i][j].getStatus().equals("M")) {
                        output += BRIGHT_GREEN + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else if (currBoard[i][j].getStatus().equals("F")) {
                        output += BRIGHT_BLUE + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }



                    }

            output += "\n";

                }
        System.out.println(output);
            }

             public String toString() {
                    String output = "   ";
                    for (int j = 0; j < columns; j++) {
                        output+= j + "  ";
                    }
                    output+="\n";
                        for (int i = 0; i < rows; i++) { //row
                           output += i + " ";
            //                if (i == rows) {
            //                    output += "\n";

                            for (int j = 0; j < columns; j++) { //col
            //                   output += j + " ";
            //                    if (j == columns) {
            //                        output += "\n";

                                if (!currBoard[i][j].getRevealed()) {
                                    if (currBoard[i][j].getStatus().equals("0")) {
                                        output += ANSI_YELLOW + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                                    }
                                    else if (currBoard[i][j].getStatus().equals("1")) {
                                        output += ANSI_GREEN + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                                    }
                                    else if (currBoard[i][j].getStatus().equals("2")) {
                                        output += ANSI_BLUE + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                                    }
                                   else  if (currBoard[i][j].getStatus().equals("3")) {
                                        output += ANSI_RED + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                                    }
                                    else if (currBoard[i][j].getStatus().equals("4")) {
                                        output += ANSI_RED_BRIGHT + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                                    }
                                    else if (currBoard[i][j].getStatus().equals("5")) {
                                        output += CYAN + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                                    }
                                    else if (currBoard[i][j].getStatus().equals("6")) {
                                        output += RED_YELLOW + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                                    }
                                    else if (currBoard[i][j].getStatus().equals("7")) {
                                        output += AQUA + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                                    }
                                    else if (currBoard[i][j].getStatus().equals("8")) {
                                        output += MAGENTA + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                                    }
                                    else if (currBoard[i][j].getStatus().equals("9")) {
                                        output += PINK + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                                    }
                                    else if (currBoard[i][j].getStatus().equals("M")) {
                                        output += BRIGHT_GREEN + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                                    }
                                    else if (currBoard[i][j].getStatus().equals("F")) {
                                        output += BRIGHT_BLUE + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                                    }
                                    else{
                                        output+="-";


                                }

                            }
                                else{
                                    output+=" - ";
                                }

                        }

                            output += "\n";
                    }
                    return output;
                }

            }