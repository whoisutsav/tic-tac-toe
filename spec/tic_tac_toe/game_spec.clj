(ns tic-tac-toe.game
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game :refer :all]
            [tic-tac-toe.board :as board]))

(describe "game-loop"
          (it "plays until there is a winner"
              (should-contain "x wins" 
                              (with-out-str (with-in-str "0\n1\n3\n4\n6" (game-loop { :board (board/new-board)
                                                                                      :markers {:x "X" :o "O"}
                                                                                      :current-player :x})))))
          (it "plays until no moves left"
                              (with-out-str (with-in-str "0\n1\n2\n3\n4\n8\n5\n6\n7" (game-loop { :board (board/new-board)
                                                                                      :markers {:x "X" :o "O"}
                                                                                      :current-player :x})))  
              
              )
          )
