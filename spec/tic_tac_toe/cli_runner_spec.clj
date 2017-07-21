(ns tic-tac-toe.cli-runner-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.cli-runner :refer :all]
            [tic-tac-toe.board :as board]))

(describe "run"
          (it "plays until there is a winner"
              (should-contain "Player X wins" 
                  (with-out-str (with-in-str "0\n1\n3\n4\n6" 
                     (run { 
                           :board (board/new-board)
                           :players {:x {:marker "X" :type :human} :o {:marker "O" :type :human}}
                           :current-player :x})))))

          (it "plays until no moves left"
             (should-contain "Cats game"
               (with-out-str (with-in-str "0\n1\n2\n3\n4\n8\n5\n6\n7" 
                  (run {
                        :board (board/new-board)
                        :players {:x {:marker "X" :type :human} :o {:marker "O" :type :human}}
                        :current-player :x}))))))
