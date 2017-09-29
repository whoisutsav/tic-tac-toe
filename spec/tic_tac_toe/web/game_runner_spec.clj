(ns tic-tac-toe.web.game-runner-spec
  (:require [speclj.core :refer :all] 
            [tic-tac-toe.web.game-runner :refer :all]))

(describe "move"
          (it "advances game and adds metadata"
              (should= {
                         :board [:O :X :O
                                 :O :X :O
                                 :X :O :X ] 
                         :current-player {:type :human-web :marker :X }
                         :opponent-player {:type :computer :marker :O}
                         :state "draw"
                         :winner nil }
                       (move {
                              :board [:O :X :O
                                      :O :X :O
                                      :_ :_ :X]
                              :current-player {:type :human-web :marker :X }
                              :opponent-player {:type :computer :marker :O}
                              :move 7
                              }))))
