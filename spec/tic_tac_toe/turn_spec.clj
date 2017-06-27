(ns tic-tac-toe.turn-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.turn :refer :all]
            [tic-tac-toe.console-output :as console-output]
            [tic-tac-toe.player :as player]))



(describe "take-turn"
          (around [it] (with-out-str (it)))

          (it "gets move and returns a turn"
              (should= {
                        :board [:x :_ :x
                                :o :o :x
                                :x :_ :_ ]
                        :markers {:x "X" :o "O"}
                        :current-player :o }
                       (with-redefs [player/get-move (constantly 5)]
                         (take-turn {
                                     :board [:x :_ :x
                                             :o :o :_
                                             :x :_ :_ ] 
                                     :markers {:x "X" :o "O"} 
                                     :current-player :x})))))
