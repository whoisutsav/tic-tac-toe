(ns tic-tac-toe.console-runner-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.console-runner :refer :all]
            [tic-tac-toe.board :as board]))

(describe "run"
          (it "plays vs. human until there is a winner"
              (should-contain "X wins" 
                  (with-out-str (with-in-str "1\n2\n4\n5\n7" 
                     (run { 
                           :board (board/new-board 3) 
                           :current-player {:marker :X :type :human}
                           :opponent-player {:marker :O :type :human}})))))

          (it "plays vs. human until no moves left"
             (should-contain "Cats game"
               (with-out-str (with-in-str "1\n2\n3\n4\n5\n9\n6\n7\n8" 
                  (run {
                        :board (board/new-board 3) 
                        :current-player {:marker :X :type :human}
                        :opponent-player {:marker :O :type :human}})))))

          (it "plays vs. computer until there is a winner/no moves left"
              (should  
                (re-find #"wins|Cats game" (with-out-str (with-in-str "1\n2\n3\n4\n5\n6\n7\n8\n9" 
                  (run {
                        :board (board/new-board 3)
                        :current-player {:marker :X :type :human}
                        :opponent-player {:marker :O :type :computer}})))))))
