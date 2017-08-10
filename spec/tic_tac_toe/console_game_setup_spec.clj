(ns tic-tac-toe.console-game-setup-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.console-game-setup :refer :all]))


(describe "new-game"
          (around [it] (with-out-str (it)))
          (it "returns new human vs human game"
              (should= {
                        :board [:_ :_ :_ 
                                :_ :_ :_ 
                                :_ :_ :_]
                        :current-player {:marker :X :type :human}
                        :opponent-player {:marker :O :type :human}}
                       (with-in-str "1\nX\nO\n" (new-game))))
          (it "returns new human vs computer game"
              (should= {
                        :board [:_ :_ :_ 
                                :_ :_ :_ 
                                :_ :_ :_]
                        :current-player {:marker :X :type :human}
                        :opponent-player {:marker :O :type :computer}}
                       (with-in-str "2\nX\n" (new-game))))
          (it "prompts until valid option"
              (should= {
                        :board [:_ :_ :_ 
                                :_ :_ :_ 
                                :_ :_ :_]
                        :current-player {:marker :X :type :human}
                        :opponent-player {:marker :O :type :computer}}
                       (with-in-str "3\n2\nX\nO\n" (new-game)))))
