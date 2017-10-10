(ns tic-tac-toe.core.player.hard-computer-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.core.player :refer [get-move]]
            [tic-tac-toe.core.player.hard-computer :refer :all]
            [tic-tac-toe.core.board :as board]
            [tic-tac-toe.core.player.spec-helper :refer [can-i-win-all-boards?]]))


(describe "hard-computer"
          (around [it] (with-out-str (it)))
          (it "is unbeatable against all possible states"
              (should= false (can-i-win-all-boards? 3 {:marker :X :type :human} {:marker :O :type :hard-computer}))))


(describe "hard-computer"
          (it "chooses the best space"
              (should= 9 (get-move {:board [:_ :x :_
                                            :o :o :x
                                            :x :x :_ ] 
                                    :current-player {:marker :o :type :hard-computer} 
                                    :opponent-player {:marker :x :type :human}}))))
