(ns tic-tac-toe.board-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :refer :all]))

(describe "generate-empty-board"
          (it "returns a board populated by nils with size axis-size*axis-size"
              (should= [
                        :_ :_ :_
                        :_ :_ :_
                        :_ :_ :_ ] (generate-empty-board))))

(describe "apply-move"
          (it "returns a board with move applied"
              (should= [
                        p1 :_ :_
                        :_ :_ :_
                        :_ :_ :_ ] (apply-move [:_ :_ :_
                                                :_ :_ :_
                                                :_ :_ :_ ] 0 1))))

(describe "is-occupied?"
          (it "returns true if cell is occupied"
              (should= true (is-occupied? 0 [p1 :_ :_
                                             :_ :_ :_
                                             :_ :_ :_ ])))
          (it "returns false if cell is not occupied"
              (should= false (is-occupied? 0 [:_ :_ :_
                                              :_ :_ :_
                                              :_ :_ :_ ]))))

(describe "over?"
          (it "returns true if game is over"
              (should= true (over? [:1 :1 :2
                                    :2 :1 :1
                                    :2 :2 :1 ])))
          (it "returns false is game is not over"
              (should= false (over? [:1 :2 :_
                                     :_ :_ :_
                                     :_ :_ :_ ]))))

(describe "winner"
          (it "returns nil for no winner"
              (should= nil (winner [:_ :_ :_
                                    :_ :_ :_
                                    :_ :_ :_])))
          (it "returns player for winning row"
              (should= 1 (winner [:1 :1 :1
                                  :_ :2 :2
                                  :_ :_ :_ ])))
          (it "returns player for winning column"
              (should= 2 (winner [:2 :1 :_
                                  :2 :1 :_
                                  :2 :_ :1]))))
