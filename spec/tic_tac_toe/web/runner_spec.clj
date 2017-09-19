(ns tic-tac-toe.web.runner-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.web.runner :refer :all]
            [tic-tac-toe.board :as board]))

(describe "handle"
          (it "takes turn"
              (should= { 
                           "game_over" false
                           "board" [:X :_ :_
                                    :_ :_ :_
                                    :_ :_ :_ ] 
                           "current-player" {:marker :O :type :human-web}
                           "opponent-player" {:marker :X :type :human-web}}
                       (handle { 
                           :board (board/new-board 3) 
                           :current-player {:marker :X :type :human-web}
                           :opponent-player {:marker :O :type :human-web}
                           :move 1})))
          (it "handles win"
              (should= { 
                           "game_over" true
                           "message" "X wins"
                           "board" [:X :O :X
                                    :O :X :O
                                    :X :_ :_ ]}
                       (handle { 
                                :board [:X :O :X
                                        :O :X :O
                                        :_ :_ :_] 
                           :current-player {:marker :X :type :hard-computer}
                           :opponent-player {:marker :O :type :human-web}
                           :move nil})))
          ; TODO convert to computer
          (it "handles draw"
              (should= { 
                           "game_over" true
                           "message" "Cats game."
                           "board" [:X :O :X
                                    :X :X :O
                                    :O :X :O]}
                       (handle { 
                           :board [:X :O :X
                                   :X :X :O
                                   :O :_ :O] 
                           :current-player {:marker :X :type :computer}
                           :opponent-player {:marker :O :type :human-web}
                           :move nil}))))
