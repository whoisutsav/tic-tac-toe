(ns tic-tac-toe.cli-runner-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.cli-runner :refer :all]
            [tic-tac-toe.board :as board]))

(describe "run"
          (it "plays until there is a winner (vs. human)"
              (should-contain "X wins" 
                  (with-out-str (with-in-str "1\n2\n4\n5\n7" 
                     (run { 
                           :board (board/new-board)
                           :current-player {:marker :X :type :human}
                           :opponent-player {:marker :O :type :human}})))))

          (it "plays until no moves left (vs. human)"
             (should-contain "Cats game"
               (with-out-str (with-in-str "1\n2\n3\n4\n5\n9\n6\n7\n8" 
                  (run {
                        :board (board/new-board)
                        :current-player {:marker :X :type :human}
                        :opponent-player {:marker :O :type :human}})))))

          (it "plays until game is over (vs. computer)"
              (should  
                (re-find #"wins|Bats game." (with-out-str (with-in-str "1\n2\n3\n4\n5\n6\n7\n8\n9" 
                  (run {
                        :board (board/new-board)
                        :current-player {:marker :X :type :human}
                        :opponent-player {:marker :O :type :computer}})))))))
