(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'kotlinx-coroutines-core'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('kotlinx-coroutines-core'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'pathalgorithm2'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'pathalgorithm2'.");
    }
    if (typeof this['kotlinx-coroutines-core'] === 'undefined') {
      throw new Error("Error loading module 'pathalgorithm2'. Its dependency 'kotlinx-coroutines-core' was not found. Please, check whether 'kotlinx-coroutines-core' is loaded prior to 'pathalgorithm2'.");
    }
    root.pathalgorithm2 = factory(typeof pathalgorithm2 === 'undefined' ? {} : pathalgorithm2, kotlin, this['kotlinx-coroutines-core']);
  }
}(this, function (_, Kotlin, $module$kotlinx_coroutines_core) {
  'use strict';
  var $$importsForInline$$ = _.$$importsForInline$$ || (_.$$importsForInline$$ = {});
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var CoroutineImpl = Kotlin.kotlin.coroutines.experimental.CoroutineImpl;
  var COROUTINE_SUSPENDED = Kotlin.kotlin.coroutines.experimental.intrinsics.COROUTINE_SUSPENDED;
  var delay = $module$kotlinx_coroutines_core.kotlinx.coroutines.experimental.delay_za3lpa$;
  var ensureNotNull = Kotlin.ensureNotNull;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var equals = Kotlin.equals;
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var addAll = Kotlin.kotlin.collections.addAll_ipc267$;
  var toMutableList = Kotlin.kotlin.collections.toMutableList_us0mfu$;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var Throwable = Error;
  var throwCCE = Kotlin.throwCCE;
  var flatten = Kotlin.kotlin.collections.flatten_yrqxlj$;
  var Unit = Kotlin.kotlin.Unit;
  var getCallableRef = Kotlin.getCallableRef;
  var IllegalStateException_init = Kotlin.kotlin.IllegalStateException_init_pdl1vj$;
  var getKClass = Kotlin.getKClass;
  var launch = $module$kotlinx_coroutines_core.kotlinx.coroutines.experimental.launch_ej4974$;
  var IllegalArgumentException_init = Kotlin.kotlin.IllegalArgumentException_init_pdl1vj$;
  var ReadWriteProperty = Kotlin.kotlin.properties.ReadWriteProperty;
  var plus = Kotlin.kotlin.collections.plus_mydzjv$;
  var defineInlineFunction = Kotlin.defineInlineFunction;
  var wrapFunction = Kotlin.wrapFunction;
  var get_js = Kotlin.kotlin.js.get_js_1yb8b7$;
  var hashCode = Kotlin.hashCode;
  var distinct = Kotlin.kotlin.collections.distinct_us0mfu$;
  var toMutableList_0 = Kotlin.kotlin.collections.toMutableList_4c7yge$;
  var getPropertyCallableRef = Kotlin.getPropertyCallableRef;
  var compareBy = Kotlin.kotlin.comparisons.compareBy_bvgy4j$;
  var sortWith = Kotlin.kotlin.collections.sortWith_nqfjgj$;
  var joinToString = Kotlin.kotlin.collections.joinToString_cgipc5$;
  Board$BoardInitException.prototype = Object.create(Throwable.prototype);
  Board$BoardInitException.prototype.constructor = Board$BoardInitException;
  Board$BoardAccessException.prototype = Object.create(Throwable.prototype);
  Board$BoardAccessException.prototype.constructor = Board$BoardAccessException;
  Board$BoardPathException.prototype = Object.create(Throwable.prototype);
  Board$BoardPathException.prototype.constructor = Board$BoardPathException;
  Board$BoardSetException.prototype = Object.create(Throwable.prototype);
  Board$BoardSetException.prototype.constructor = Board$BoardSetException;
  function BreathFirstSearch() {
  }
  function BreathFirstSearch$Entry(position, previous) {
    BreathFirstSearch$Entry$Factory_getInstance();
    this.position = position;
    this.previous = previous;
  }
  function BreathFirstSearch$Entry$Factory() {
    BreathFirstSearch$Entry$Factory_instance = this;
    this.NOT_FOUND = new BreathFirstSearch$Entry(new Position_0(-1, -1), null);
  }
  BreathFirstSearch$Entry$Factory.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Factory',
    interfaces: []
  };
  var BreathFirstSearch$Entry$Factory_instance = null;
  function BreathFirstSearch$Entry$Factory_getInstance() {
    if (BreathFirstSearch$Entry$Factory_instance === null) {
      new BreathFirstSearch$Entry$Factory();
    }
    return BreathFirstSearch$Entry$Factory_instance;
  }
  BreathFirstSearch$Entry.prototype.collectPath = function () {
    if (Kotlin.isType(this.previous, BreathFirstSearch$Entry))
      return (new Path([this.position])).plus_1mz6r6$(this.previous.collectPath());
    else
      return new Path([this.position]);
  };
  BreathFirstSearch$Entry.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Entry',
    interfaces: []
  };
  BreathFirstSearch$Entry.prototype.component1 = function () {
    return this.position;
  };
  BreathFirstSearch$Entry.prototype.component2 = function () {
    return this.previous;
  };
  BreathFirstSearch$Entry.prototype.copy_q799mk$ = function (position, previous) {
    return new BreathFirstSearch$Entry(position === void 0 ? this.position : position, previous === void 0 ? this.previous : previous);
  };
  BreathFirstSearch$Entry.prototype.toString = function () {
    return 'Entry(position=' + Kotlin.toString(this.position) + (', previous=' + Kotlin.toString(this.previous)) + ')';
  };
  BreathFirstSearch$Entry.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.position) | 0;
    result = result * 31 + Kotlin.hashCode(this.previous) | 0;
    return result;
  };
  BreathFirstSearch$Entry.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.position, other.position) && Kotlin.equals(this.previous, other.previous)))));
  };
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  BreathFirstSearch.prototype.solve_kzwmta$$default = function (board_0, initialWait_0, drawWait_0, continuation_0, suspended) {
    var instance = new Coroutine$solve_kzwmta$$default(this, board_0, initialWait_0, drawWait_0, continuation_0);
    if (suspended)
      return instance;
    else
      return instance.doResume(null);
  };
  function Coroutine$solve_kzwmta$$default($this, board_0, initialWait_0, drawWait_0, continuation_0) {
    CoroutineImpl.call(this, continuation_0);
    this.exceptionState_0 = 1;
    this.$this = $this;
    this.local$rest = void 0;
    this.local$searchResult = void 0;
    this.local$entry = void 0;
    this.local$currentTile = void 0;
    this.local$board = board_0;
    this.local$initialWait = initialWait_0;
    this.local$drawWait = drawWait_0;
  }
  Coroutine$solve_kzwmta$$default.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    simpleName: null,
    interfaces: [CoroutineImpl]
  };
  Coroutine$solve_kzwmta$$default.prototype = Object.create(CoroutineImpl.prototype);
  Coroutine$solve_kzwmta$$default.prototype.constructor = Coroutine$solve_kzwmta$$default;
  Coroutine$solve_kzwmta$$default.prototype.doResume = function () {
    do
      try {
        switch (this.state_0) {
          case 0:
            var tmp$;
            this.state_0 = 2;
            this.result_0 = delay(this.local$initialWait, this);
            if (this.result_0 === COROUTINE_SUSPENDED)
              return COROUTINE_SUSPENDED;
            continue;
          case 1:
            throw this.exception_0;
          case 2:
            var startTile = this.local$board.findStart();
            this.local$rest = new Queue([new BreathFirstSearch$Entry(startTile.position, null)]);
            this.local$searchResult = BreathFirstSearch$Entry$Factory_getInstance().NOT_FOUND;
            this.state_0 = 3;
            continue;
          case 3:
            if (!this.local$rest.isNotEmpty()) {
              this.state_0 = 7;
              continue;
            }

            this.local$entry = ensureNotNull(this.local$rest.dequeue());
            this.local$currentTile = this.local$board.getTileOn_j3yxri$(this.local$entry.position);
            this.state_0 = 4;
            this.result_0 = delay(this.local$drawWait, this);
            if (this.result_0 === COROUTINE_SUSPENDED)
              return COROUTINE_SUSPENDED;
            continue;
          case 4:
            if (Kotlin.isType(this.local$currentTile, GoalTile)) {
              this.local$searchResult = this.local$entry;
              this.state_0 = 7;
              continue;
            }
             else {
              this.state_0 = 5;
              continue;
            }

          case 5:
            if (Kotlin.isType(this.local$currentTile, VisitedTile)) {
              println('Should never revisited a tile ' + this.local$currentTile + '.position2');
              this.state_0 = 3;
              continue;
            }
             else {
              this.state_0 = 6;
              continue;
            }

          case 6:
            var $receiver = this.local$board.getNeighboursOn_j3yxri$(this.local$entry.position).toList();
            var destination = ArrayList_init();
            var tmp$_0;
            tmp$_0 = $receiver.iterator();
            while (tmp$_0.hasNext()) {
              var element = tmp$_0.next();
              if (Kotlin.isType(element, GoalTile) || Kotlin.isType(element, EmptyTile) || Kotlin.isType(element, WatchedTile))
                destination.add_11rb$(element);
            }

            var destination_0 = ArrayList_init();
            var tmp$_1;
            tmp$_1 = destination.iterator();
            while (tmp$_1.hasNext()) {
              var element_0 = tmp$_1.next();
              var $receiver_0 = this.local$rest.items;
              var destination_1 = ArrayList_init(collectionSizeOrDefault($receiver_0, 10));
              var tmp$_2;
              tmp$_2 = $receiver_0.iterator();
              while (tmp$_2.hasNext()) {
                var item = tmp$_2.next();
                destination_1.add_11rb$(item.position);
              }
              if (!destination_1.contains_11rb$(element_0.position))
                destination_0.add_11rb$(element_0);
            }

            var movableNeighbours = destination_0;
            var destination_2 = ArrayList_init(collectionSizeOrDefault(movableNeighbours, 10));
            var tmp$_3;
            tmp$_3 = movableNeighbours.iterator();
            while (tmp$_3.hasNext()) {
              var item_0 = tmp$_3.next();
              destination_2.add_11rb$(new BreathFirstSearch$Entry(item_0.position, this.local$entry));
            }

            this.local$rest.enqueue_p1ys8y$(destination_2);
            this.state_0 = 3;
            continue;
          case 7:
            if (equals(this.local$searchResult, BreathFirstSearch$Entry$Factory_getInstance().NOT_FOUND))
              tmp$ = new PathFindingAlgorithm$PathFindingResult(false, new Path([]));
            else {
              this.local$board.setPath_1mz6r6$(this.local$searchResult.collectPath());
              tmp$ = new PathFindingAlgorithm$PathFindingResult(true, this.local$searchResult.collectPath());
            }

            return tmp$;
        }
      }
       catch (e) {
        if (this.state_0 === 1) {
          this.exceptionState_0 = this.state_0;
          throw e;
        }
         else {
          this.state_0 = this.exceptionState_0;
          this.exception_0 = e;
        }
      }
     while (true);
  };
  BreathFirstSearch.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BreathFirstSearch',
    interfaces: [PathFindingAlgorithm]
  };
  function DepthFirstSearchFail() {
  }
  DepthFirstSearchFail.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DepthFirstSearchFail',
    interfaces: []
  };
  function PathFindingAlgorithm() {
  }
  function PathFindingAlgorithm$PathFindingResult(solved, path) {
    this.solved = solved;
    this.path = path;
  }
  PathFindingAlgorithm$PathFindingResult.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PathFindingResult',
    interfaces: []
  };
  PathFindingAlgorithm$PathFindingResult.prototype.component1 = function () {
    return this.solved;
  };
  PathFindingAlgorithm$PathFindingResult.prototype.component2 = function () {
    return this.path;
  };
  PathFindingAlgorithm$PathFindingResult.prototype.copy_j1aurj$ = function (solved, path) {
    return new PathFindingAlgorithm$PathFindingResult(solved === void 0 ? this.solved : solved, path === void 0 ? this.path : path);
  };
  PathFindingAlgorithm$PathFindingResult.prototype.toString = function () {
    return 'PathFindingResult(solved=' + Kotlin.toString(this.solved) + (', path=' + Kotlin.toString(this.path)) + ')';
  };
  PathFindingAlgorithm$PathFindingResult.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.solved) | 0;
    result = result * 31 + Kotlin.hashCode(this.path) | 0;
    return result;
  };
  PathFindingAlgorithm$PathFindingResult.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.solved, other.solved) && Kotlin.equals(this.path, other.path)))));
  };
  PathFindingAlgorithm.prototype.solveWithoutDelay_odmita$ = function (board, continuation) {
    return this.solve_kzwmta$(board, 0, 0, continuation);
  };
  PathFindingAlgorithm.prototype.solve_kzwmta$ = function (board, initialWait, drawWait, continuation, callback$default) {
    if (initialWait === void 0)
      initialWait = 1000;
    if (drawWait === void 0)
      drawWait = 50;
    return callback$default ? callback$default(board, initialWait, drawWait, continuation) : this.solve_kzwmta$$default(board, initialWait, drawWait, continuation);
  };
  PathFindingAlgorithm.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'PathFindingAlgorithm',
    interfaces: []
  };
  function Queue(entries) {
    this.items = toMutableList(entries);
  }
  Queue.prototype.isEmpty = function () {
    return this.items.isEmpty();
  };
  Queue.prototype.isNotEmpty = function () {
    return !this.items.isEmpty();
  };
  Queue.prototype.count = function () {
    return this.items.size;
  };
  Queue.prototype.toString = function () {
    return this.items.toString();
  };
  Queue.prototype.enqueue_11rb$ = function (element) {
    this.items.add_11rb$(element);
  };
  Queue.prototype.enqueue_p1ys8y$ = function (element) {
    addAll(this.items, element);
  };
  Queue.prototype.dequeue = function () {
    if (this.isEmpty()) {
      return null;
    }
     else {
      return this.items.removeAt_za3lpa$(0);
    }
  };
  Queue.prototype.peek = function () {
    return this.items.get_za3lpa$(0);
  };
  Queue.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Queue',
    interfaces: []
  };
  var UnsupportedOperationException_init = Kotlin.kotlin.UnsupportedOperationException_init_pdl1vj$;
  var get_lastIndex = Kotlin.kotlin.collections.get_lastIndex_m7z4lg$;
  function Board(ui, tilesSupplier) {
    Board$Factory_getInstance();
    this.tiles_0 = tilesSupplier();
    this.boardSize_0 = null;
    this.ui_0 = null;
    if (this.tiles_0.length === 0)
      throw new Board$BoardInitException('Outer Array is empty');
    if (this.tiles_0[0].length === 0) {
      throw new Board$BoardInitException('Inner Array is empty: ' + this.tiles_0 + '[0]');
    }
    var $receiver = this.tiles_0;
    var tmp$, tmp$_0;
    var index = 0;
    for (tmp$ = 0; tmp$ !== $receiver.length; ++tmp$) {
      var item = $receiver[tmp$];
      var x = (tmp$_0 = index, index = tmp$_0 + 1 | 0, tmp$_0);
      var tmp$_1, tmp$_0_0;
      var index_0 = 0;
      for (tmp$_1 = 0; tmp$_1 !== item.length; ++tmp$_1) {
        var item_0 = item[tmp$_1];
        var y = (tmp$_0_0 = index_0, index_0 = tmp$_0_0 + 1 | 0, tmp$_0_0);
        var tmp$_2;
        if (!((tmp$_2 = item_0.position) != null ? tmp$_2.equals(new Position_0(x, y)) : null)) {
          throw new Board$BoardInitException('Misconfiguration of Tiles: TilePos: ' + item_0.position + ' != ' + new Position_0(x, y));
        }
      }
    }
    var $receiver_0 = this.tiles_0;
    var tmp$_3;
    if ($receiver_0.length === 0)
      throw UnsupportedOperationException_init("Empty array can't be reduced.");
    var accumulator = $receiver_0[0];
    tmp$_3 = get_lastIndex($receiver_0);
    for (var index_1 = 1; index_1 <= tmp$_3; index_1++) {
      var p = accumulator;
      var c = $receiver_0[index_1];
      var operation$result;
      if (p.length !== c.length)
        throw new Board$BoardInitException('Columns does not have same size: ' + p.length + ' != ' + c.length);
      else {
        operation$result = c;
      }
      accumulator = operation$result;
    }
    this.boardSize_0 = new Board$BoardSize(this.tiles_0.length, this.tiles_0[0].length);
    ui.drawTiles_3atklz$(this.tiles_0);
    this.ui_0 = ui;
  }
  function Board$BoardSize(width, height) {
    this.width = width;
    this.height = height;
  }
  Board$BoardSize.prototype.isInBoard_do1ejz$ = function (position) {
    return position.x < this.width && position.y < this.height && (position.x >= 0 && position.y >= 0);
  };
  Board$BoardSize.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BoardSize',
    interfaces: []
  };
  Board$BoardSize.prototype.component1 = function () {
    return this.width;
  };
  Board$BoardSize.prototype.component2 = function () {
    return this.height;
  };
  Board$BoardSize.prototype.copy_vux9f0$ = function (width, height) {
    return new Board$BoardSize(width === void 0 ? this.width : width, height === void 0 ? this.height : height);
  };
  Board$BoardSize.prototype.toString = function () {
    return 'BoardSize(width=' + Kotlin.toString(this.width) + (', height=' + Kotlin.toString(this.height)) + ')';
  };
  Board$BoardSize.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.width) | 0;
    result = result * 31 + Kotlin.hashCode(this.height) | 0;
    return result;
  };
  Board$BoardSize.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.width, other.width) && Kotlin.equals(this.height, other.height)))));
  };
  function Board$NeighbourTiles(top, right, bottom, left) {
    this.top = top;
    this.right = right;
    this.bottom = bottom;
    this.left = left;
  }
  Board$NeighbourTiles.prototype.toList = function () {
    return listOf([this.top, this.right, this.bottom, this.left]);
  };
  Board$NeighbourTiles.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NeighbourTiles',
    interfaces: []
  };
  Board$NeighbourTiles.prototype.component1 = function () {
    return this.top;
  };
  Board$NeighbourTiles.prototype.component2 = function () {
    return this.right;
  };
  Board$NeighbourTiles.prototype.component3 = function () {
    return this.bottom;
  };
  Board$NeighbourTiles.prototype.component4 = function () {
    return this.left;
  };
  Board$NeighbourTiles.prototype.copy_m9k178$ = function (top, right, bottom, left) {
    return new Board$NeighbourTiles(top === void 0 ? this.top : top, right === void 0 ? this.right : right, bottom === void 0 ? this.bottom : bottom, left === void 0 ? this.left : left);
  };
  Board$NeighbourTiles.prototype.toString = function () {
    return 'NeighbourTiles(top=' + Kotlin.toString(this.top) + (', right=' + Kotlin.toString(this.right)) + (', bottom=' + Kotlin.toString(this.bottom)) + (', left=' + Kotlin.toString(this.left)) + ')';
  };
  Board$NeighbourTiles.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.top) | 0;
    result = result * 31 + Kotlin.hashCode(this.right) | 0;
    result = result * 31 + Kotlin.hashCode(this.bottom) | 0;
    result = result * 31 + Kotlin.hashCode(this.left) | 0;
    return result;
  };
  Board$NeighbourTiles.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.top, other.top) && Kotlin.equals(this.right, other.right) && Kotlin.equals(this.bottom, other.bottom) && Kotlin.equals(this.left, other.left)))));
  };
  function Board$BoardInitException(s) {
    Throwable.call(this);
    this.message_4sx3co$_0 = s;
    this.cause_uoshna$_0 = null;
    Kotlin.captureStack(Throwable, this);
    this.name = 'Board$BoardInitException';
  }
  Object.defineProperty(Board$BoardInitException.prototype, 'message', {
    get: function () {
      return this.message_4sx3co$_0;
    }
  });
  Object.defineProperty(Board$BoardInitException.prototype, 'cause', {
    get: function () {
      return this.cause_uoshna$_0;
    }
  });
  Board$BoardInitException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BoardInitException',
    interfaces: [Throwable]
  };
  function Board$BoardAccessException(s) {
    Throwable.call(this);
    this.message_hune30$_0 = s;
    this.cause_lgo3e2$_0 = null;
    Kotlin.captureStack(Throwable, this);
    this.name = 'Board$BoardAccessException';
  }
  Object.defineProperty(Board$BoardAccessException.prototype, 'message', {
    get: function () {
      return this.message_hune30$_0;
    }
  });
  Object.defineProperty(Board$BoardAccessException.prototype, 'cause', {
    get: function () {
      return this.cause_lgo3e2$_0;
    }
  });
  Board$BoardAccessException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BoardAccessException',
    interfaces: [Throwable]
  };
  function Board$BoardPathException(s) {
    Throwable.call(this);
    this.message_tqgy6l$_0 = s;
    this.cause_x4u51$_0 = null;
    Kotlin.captureStack(Throwable, this);
    this.name = 'Board$BoardPathException';
  }
  Object.defineProperty(Board$BoardPathException.prototype, 'message', {
    get: function () {
      return this.message_tqgy6l$_0;
    }
  });
  Object.defineProperty(Board$BoardPathException.prototype, 'cause', {
    get: function () {
      return this.cause_x4u51$_0;
    }
  });
  Board$BoardPathException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BoardPathException',
    interfaces: [Throwable]
  };
  function Board$BoardSetException(s) {
    Throwable.call(this);
    this.message_u723tq$_0 = s;
    this.cause_5aeb24$_0 = null;
    Kotlin.captureStack(Throwable, this);
    this.name = 'Board$BoardSetException';
  }
  Object.defineProperty(Board$BoardSetException.prototype, 'message', {
    get: function () {
      return this.message_u723tq$_0;
    }
  });
  Object.defineProperty(Board$BoardSetException.prototype, 'cause', {
    get: function () {
      return this.cause_5aeb24$_0;
    }
  });
  Board$BoardSetException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BoardSetException',
    interfaces: [Throwable]
  };
  function Board$Factory() {
    Board$Factory_instance = this;
  }
  var Array_0 = Array;
  function Board$Factory$createEmpty$lambda(closure$size) {
    return function () {
      var size = closure$size.width;
      var array = Array_0(size);
      var tmp$;
      tmp$ = array.length - 1 | 0;
      for (var i = 0; i <= tmp$; i++) {
        var array_0 = Array_0(closure$size.height);
        var tmp$_0;
        tmp$_0 = array_0.length - 1 | 0;
        for (var i_0 = 0; i_0 <= tmp$_0; i_0++) {
          var tmp$_1;
          array_0[i_0] = Kotlin.isType(tmp$_1 = EmptyTile_init(new Position_0(i, i_0)), Tile) ? tmp$_1 : throwCCE();
        }
        array[i] = array_0;
      }
      return array;
    };
  }
  Board$Factory.prototype.createEmpty_oxi185$ = function (size, ui) {
    return new Board(ui, Board$Factory$createEmpty$lambda(size));
  };
  Board$Factory.prototype.create_x7l24f$ = function (ui, supplier) {
    return new Board(ui, supplier);
  };
  Board$Factory.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Factory',
    interfaces: []
  };
  var Board$Factory_instance = null;
  function Board$Factory_getInstance() {
    if (Board$Factory_instance === null) {
      new Board$Factory();
    }
    return Board$Factory_instance;
  }
  Board.prototype.boardSize = function () {
    return this.boardSize_0;
  };
  Board.prototype.isPlayable = function () {
    var flattenTiles = flatten(this.tiles_0);
    var destination = ArrayList_init();
    var tmp$;
    tmp$ = flattenTiles.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      if (Kotlin.isType(element, StartTile))
        destination.add_11rb$(element);
    }
    var tmp$_0 = destination.size === 1;
    if (tmp$_0) {
      var destination_0 = ArrayList_init();
      var tmp$_1;
      tmp$_1 = flattenTiles.iterator();
      while (tmp$_1.hasNext()) {
        var element_0 = tmp$_1.next();
        if (Kotlin.isType(element_0, GoalTile))
          destination_0.add_11rb$(element_0);
      }
      tmp$_0 = destination_0.size === 1;
    }
    return tmp$_0;
  };
  Board.prototype.getTileOn_j3yxri$ = function (position) {
    if (this.boardSize_0.isInBoard_do1ejz$(position))
      return this.tiles_0[position.x][position.y];
    else
      throw new Board$BoardAccessException(position.toString() + ' is not inside of ' + this.boardSize_0);
  };
  Board.prototype.getTileOn_in2pv6$ = function (supplier) {
    return this.getTileOn_j3yxri$(supplier());
  };
  function Board$getTileOn$lambda(closure$x, closure$y) {
    return function () {
      return new Position_0(closure$x, closure$y);
    };
  }
  Board.prototype.getTileOn_vux9f0$ = function (x, y) {
    return this.getTileOn_in2pv6$(Board$getTileOn$lambda(x, y));
  };
  Board.prototype.setTile_0 = function (tile) {
    if (this.boardSize_0.isInBoard_do1ejz$(tile.position))
      this.tiles_0[tile.position.x][tile.position.y] = tile;
    else
      throw new Board$BoardSetException('Tile position2 ' + tile.position + ' is not inside of ' + this.boardSize_0);
  };
  Board.prototype.getNeighboursOn_j3yxri$ = function (position) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    if (!this.boardSize_0.isInBoard_do1ejz$(position))
      throw new Board$BoardAccessException(position.toString() + ' is not inside of ' + this.boardSize_0);
    if ((position.y - 1 | 0) < 0)
      tmp$ = WallTile_init(new Position_0(position.x, position.y - 1 | 0));
    else
      tmp$ = this.getTileOn_j3yxri$(new Position_0(position.x, position.y - 1 | 0));
    if ((position.x + 1 | 0) > (this.boardSize_0.width - 1 | 0))
      tmp$_0 = WallTile_init(new Position_0(position.x + 1 | 0, position.y));
    else
      tmp$_0 = this.getTileOn_j3yxri$(new Position_0(position.x + 1 | 0, position.y));
    if ((position.y + 1 | 0) > (this.boardSize_0.height - 1 | 0))
      tmp$_1 = WallTile_init(new Position_0(position.x, position.y + 1 | 0));
    else
      tmp$_1 = this.getTileOn_j3yxri$(new Position_0(position.x, position.y + 1 | 0));
    if ((position.x - 1 | 0) < 0)
      tmp$_2 = WallTile_init(new Position_0(position.x - 1 | 0, position.y));
    else
      tmp$_2 = this.getTileOn_j3yxri$(new Position_0(position.x - 1 | 0, position.y));
    var neighbours = new Board$NeighbourTiles(tmp$, tmp$_0, tmp$_1, tmp$_2);
    var $receiver = neighbours.toList();
    var destination = ArrayList_init();
    var tmp$_3;
    tmp$_3 = $receiver.iterator();
    while (tmp$_3.hasNext()) {
      var element = tmp$_3.next();
      if (Kotlin.isType(element, EmptyTile))
        destination.add_11rb$(element);
    }
    var destination_0 = ArrayList_init(collectionSizeOrDefault(destination, 10));
    var tmp$_4;
    tmp$_4 = destination.iterator();
    while (tmp$_4.hasNext()) {
      var item = tmp$_4.next();
      destination_0.add_11rb$(WatchedTile_init(item.position));
    }
    var action = getCallableRef('setTile', function ($receiver, tile) {
      return $receiver.setTile_0(tile), Unit;
    }.bind(null, this));
    var tmp$_5;
    tmp$_5 = destination_0.iterator();
    while (tmp$_5.hasNext()) {
      var element_0 = tmp$_5.next();
      action(element_0);
    }
    var visitedTile = this.getTileOn_j3yxri$(position);
    if (Kotlin.isType(visitedTile, WalkableTile))
      this.setTile_0(VisitedTile_init(visitedTile.position));
    this.drawUi_0();
    return neighbours;
  };
  Board.prototype.getNeighboursOn_in2pv6$ = function (supplier) {
    return this.getNeighboursOn_j3yxri$(supplier());
  };
  function Board$getNeighboursOn$lambda(closure$x, closure$y) {
    return function () {
      return new Position_0(closure$x, closure$y);
    };
  }
  Board.prototype.getNeighboursOn_vux9f0$ = function (x, y) {
    return this.getNeighboursOn_in2pv6$(Board$getNeighboursOn$lambda(x, y));
  };
  Board.prototype.findUniqueTile_whpll$ = function (clazz) {
    var tmp$, tmp$_0;
    if (!this.isPlayable()) {
      throw IllegalStateException_init('Board is not playable.');
    }
    var $receiver = flatten(this.tiles_0);
    var firstOrNull$result;
    firstOrNull$break: do {
      var tmp$_1;
      tmp$_1 = $receiver.iterator();
      while (tmp$_1.hasNext()) {
        var element = tmp$_1.next();
        if (clazz.isInstance_s8jyv4$(element)) {
          firstOrNull$result = element;
          break firstOrNull$break;
        }
      }
      firstOrNull$result = null;
    }
     while (false);
    var result = firstOrNull$result;
    if (clazz.isInstance_s8jyv4$(result))
      tmp$_0 = Kotlin.isType(tmp$ = result, UniqueTile) ? tmp$ : throwCCE();
    else
      throw IllegalStateException_init('Unique tile not found');
    return tmp$_0;
  };
  Board.prototype.findStart = function () {
    return this.findUniqueTile_whpll$(getKClass(StartTile));
  };
  Board.prototype.findGoal = function () {
    return this.findUniqueTile_whpll$(getKClass(GoalTile));
  };
  Board.prototype.setPath_1mz6r6$ = function (path) {
    var tmp$;
    tmp$ = path.iterator();
    while (tmp$.hasNext()) {
      var position = tmp$.next();
      if (!this.boardSize_0.isInBoard_do1ejz$(position))
        throw new Board$BoardPathException('Path element ' + position + ' is outside of the board ' + this.boardSize_0);
      else if (Kotlin.isType(this.getTileOn_j3yxri$(position), WallTile))
        throw new Board$BoardPathException('Path element ' + position + ' is a wall tile');
    }
    var $receiver = path.positions;
    var destination = ArrayList_init();
    var tmp$_0;
    tmp$_0 = $receiver.iterator();
    while (tmp$_0.hasNext()) {
      var element = tmp$_0.next();
      if (getKClass(WalkableTile).isInstance_s8jyv4$(this.getTileOn_j3yxri$(element)))
        destination.add_11rb$(element);
    }
    var destination_0 = ArrayList_init(collectionSizeOrDefault(destination, 10));
    var tmp$_1;
    tmp$_1 = destination.iterator();
    while (tmp$_1.hasNext()) {
      var item = tmp$_1.next();
      destination_0.add_11rb$(PathTile_init(item));
    }
    var action = getCallableRef('setTile', function ($receiver, tile) {
      return $receiver.setTile_0(tile), Unit;
    }.bind(null, this));
    var tmp$_2;
    tmp$_2 = destination_0.iterator();
    while (tmp$_2.hasNext()) {
      var element_0 = tmp$_2.next();
      action(element_0);
    }
    this.drawUi_0();
  };
  Board.prototype.drawUi_0 = function () {
    this.ui_0.drawTiles_3atklz$(this.tiles_0);
  };
  Board.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Board',
    interfaces: []
  };
  function angleLevelWithGoalOn_x13_y3$lambda() {
    var array = Array_0(15);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      var array_0 = Array_0(12);
      var tmp$_0;
      tmp$_0 = array_0.length - 1 | 0;
      for (var i_0 = 0; i_0 <= tmp$_0; i_0++) {
        var init$result;
        if (i === 13 & i_0 === 3) {
          init$result = GoalTile_init_0(i, i_0);
        }
         else if ((3 <= i && i <= 12) & i_0 === 3) {
          init$result = WallTile_init_0(i, i_0);
        }
         else if (i === 12 & (3 <= i_0 && i_0 <= 9)) {
          init$result = WallTile_init_0(i, i_0);
        }
         else if (i === 2 & i_0 === 11) {
          init$result = StartTile_init_0(i, i_0);
        }
         else {
          init$result = EmptyTile_init_0(i, i_0);
        }
        array_0[i_0] = init$result;
      }
      array[i] = array_0;
    }
    return array;
  }
  function angleLevelWithGoalOn_x13_y3($receiver, ui) {
    return Board$Factory_getInstance().create_x7l24f$(ui, angleLevelWithGoalOn_x13_y3$lambda);
  }
  function weirdLevel$lambda() {
    var array = Array_0(15);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      var array_0 = Array_0(10);
      var tmp$_0;
      tmp$_0 = array_0.length - 1 | 0;
      for (var i_0 = 0; i_0 <= tmp$_0; i_0++) {
        var init$result;
        if (i === 5 & i_0 === 0) {
          init$result = GoalTile_init_0(i, i_0);
        }
         else if (i === 6 & (0 <= i_0 && i_0 <= 1)) {
          init$result = WallTile_init_0(i, i_0);
        }
         else if (i === 10 & (1 <= i_0 && i_0 <= 2)) {
          init$result = WallTile_init_0(i, i_0);
        }
         else if ((0 <= i && i <= 2 || (4 <= i && i <= 10) || (12 <= i && i <= 15)) & i_0 === 3) {
          init$result = WallTile_init_0(i, i_0);
        }
         else if ((0 <= i && i <= 1 || (3 <= i && i <= 15)) & i_0 === 5) {
          init$result = WallTile_init_0(i, i_0);
        }
         else if ((0 <= i && i <= 10 || (12 <= i && i <= 15)) & i_0 === 7) {
          init$result = WallTile_init_0(i, i_0);
        }
         else if (i === 2 & i_0 === 9) {
          init$result = StartTile_init_0(i, i_0);
        }
         else {
          init$result = EmptyTile_init_0(i, i_0);
        }
        array_0[i_0] = init$result;
      }
      array[i] = array_0;
    }
    return array;
  }
  function weirdLevel($receiver, ui) {
    return Board$Factory_getInstance().create_x7l24f$(ui, weirdLevel$lambda);
  }
  function Bootstrap() {
    Bootstrap_instance = this;
  }
  function Bootstrap$bootstrap$lambda(closure$ui_0) {
    return function ($receiver, continuation_0, suspended) {
      var instance = new Coroutine$Bootstrap$bootstrap$lambda(closure$ui_0, $receiver, this, continuation_0);
      if (suspended)
        return instance;
      else
        return instance.doResume(null);
    };
  }
  function Coroutine$Bootstrap$bootstrap$lambda(closure$ui_0, $receiver, controller, continuation_0) {
    CoroutineImpl.call(this, continuation_0);
    this.$controller = controller;
    this.exceptionState_0 = 1;
    this.local$closure$ui = closure$ui_0;
  }
  Coroutine$Bootstrap$bootstrap$lambda.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    simpleName: null,
    interfaces: [CoroutineImpl]
  };
  Coroutine$Bootstrap$bootstrap$lambda.prototype = Object.create(CoroutineImpl.prototype);
  Coroutine$Bootstrap$bootstrap$lambda.prototype.constructor = Coroutine$Bootstrap$bootstrap$lambda;
  Coroutine$Bootstrap$bootstrap$lambda.prototype.doResume = function () {
    do
      try {
        switch (this.state_0) {
          case 0:
            var board = angleLevelWithGoalOn_x13_y3(Board$Factory_getInstance(), this.local$closure$ui);
            this.state_0 = 2;
            this.result_0 = (new BreathFirstSearch()).solve_kzwmta$(board, void 0, void 0, this);
            if (this.result_0 === COROUTINE_SUSPENDED)
              return COROUTINE_SUSPENDED;
            continue;
          case 1:
            throw this.exception_0;
          case 2:
            return this.result_0;
        }
      }
       catch (e) {
        if (this.state_0 === 1) {
          this.exceptionState_0 = this.state_0;
          throw e;
        }
         else {
          this.state_0 = this.exceptionState_0;
          this.exception_0 = e;
        }
      }
     while (true);
  };
  Bootstrap.prototype.bootstrap_xqhy74$ = function (ui) {
    launch(void 0, void 0, void 0, Bootstrap$bootstrap$lambda(ui));
  };
  Bootstrap.prototype.bootstrapJS = function () {
    this.bootstrap_xqhy74$(RoughJsUi_getInstance());
  };
  Bootstrap.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Bootstrap',
    interfaces: []
  };
  var Bootstrap_instance = null;
  function Bootstrap_getInstance() {
    if (Bootstrap_instance === null) {
      new Bootstrap();
    }
    return Bootstrap_instance;
  }
  function Position(x, y) {
    this.x = x;
    this.y = y;
  }
  Position.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Position',
    interfaces: []
  };
  Position.prototype.component1 = function () {
    return this.x;
  };
  Position.prototype.component2 = function () {
    return this.y;
  };
  Position.prototype.copy_vux9f0$ = function (x, y) {
    return new Position(x === void 0 ? this.x : x, y === void 0 ? this.y : y);
  };
  Position.prototype.toString = function () {
    return 'Position(x=' + Kotlin.toString(this.x) + (', y=' + Kotlin.toString(this.y)) + ')';
  };
  Position.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.x) | 0;
    result = result * 31 + Kotlin.hashCode(this.y) | 0;
    return result;
  };
  Position.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.x, other.x) && Kotlin.equals(this.y, other.y)))));
  };
  function NotNegative(defaultValue) {
    this.value_0 = defaultValue;
  }
  NotNegative.prototype.getValue_lrcp0p$ = function (thisRef, property) {
    return this.value_0;
  };
  NotNegative.prototype.setValue_9rddgb$ = function (thisRef, property, value) {
    var tmp$;
    if (typeof value === 'number' && value < 0)
      throw IllegalArgumentException_init('Negative values are not supported.');
    else if (typeof value === 'number' && value < 0)
      throw IllegalArgumentException_init('Negative values are not supported.');
    else if (typeof value === 'number' && value < 0)
      throw IllegalArgumentException_init('Negative values are not supported.');
    else
      tmp$ = value;
    this.value_0 = tmp$;
  };
  NotNegative.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NotNegative',
    interfaces: [ReadWriteProperty]
  };
  function Path(positions) {
    Path$Factory_getInstance();
    this.positions = null;
    this.size = 0;
    var tmpList = toMutableList_0(distinct(positions));
    sortWith(tmpList, compareBy([getPropertyCallableRef('x', 1, function ($receiver) {
      return $receiver.x;
    }), getPropertyCallableRef('y', 1, function ($receiver) {
      return $receiver.y;
    })]));
    this.positions = tmpList;
    this.size = this.positions.size;
  }
  function Path$Factory() {
    Path$Factory_instance = this;
    this.EMPTY_PATH_0 = new Path([]);
  }
  Path$Factory.prototype.createEmpty = function () {
    return this.EMPTY_PATH_0;
  };
  Path$Factory.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Factory',
    interfaces: []
  };
  var Path$Factory_instance = null;
  function Path$Factory_getInstance() {
    if (Path$Factory_instance === null) {
      new Path$Factory();
    }
    return Path$Factory_instance;
  }
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  Path.prototype.plus_1mz6r6$ = function (path) {
    return new Path(copyToArray(plus(this.positions, path.positions)).slice());
  };
  Path.prototype.iterator = function () {
    return this.positions.iterator();
  };
  Path.prototype.filter_yu1d11$ = defineInlineFunction('pathalgorithm2.tiles.Path.filter_yu1d11$', wrapFunction(function () {
    var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
    return function (predicate) {
      var $receiver = this.positions;
      var destination = ArrayList_init();
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        if (predicate(element))
          destination.add_11rb$(element);
      }
      return destination;
    };
  }));
  Path.prototype.isValid = function () {
    var iterator = this.positions.iterator();
    if (!iterator.hasNext())
      throw UnsupportedOperationException_init("Empty collection can't be reduced.");
    var accumulator = iterator.next();
    while (iterator.hasNext()) {
      var p = accumulator;
      var c = iterator.next();
      var operation$result;
      var tmp$, tmp$_0, tmp$_1;
      tmp$ = p.x - c.x | 0;
      if (-1 <= tmp$ && tmp$ <= 1) {
        tmp$_0 = p.y - c.y | 0;
        tmp$_1 = (-1 <= tmp$_0 && tmp$_0 <= 1);
      }
       else
        tmp$_1 = false;
      if (tmp$_1) {
        operation$result = c;
      }
       else
        return false;
      accumulator = operation$result;
    }
    return true;
  };
  Path.prototype.equals = function (other) {
    var tmp$;
    if (this === other)
      return true;
    if (other == null || !equals(get_js(Kotlin.getKClassFromExpression(this)), get_js(Kotlin.getKClassFromExpression(other))))
      return false;
    Kotlin.isType(tmp$ = other, Path) ? tmp$ : throwCCE();
    if (!equals(this.positions, other.positions))
      return false;
    return true;
  };
  Path.prototype.hashCode = function () {
    return hashCode(this.positions);
  };
  Path.prototype.toString = function () {
    return 'Path(positions=' + this.positions + ')';
  };
  Path.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Path',
    interfaces: []
  };
  function Path_init(supplier, $this) {
    $this = $this || Object.create(Path.prototype);
    Path.call($this, [supplier()]);
    return $this;
  }
  function Path_init_0(x, y, $this) {
    $this = $this || Object.create(Path.prototype);
    Path.call($this, [new Position_0(x, y)]);
    return $this;
  }
  function Position_0(x, y) {
    this.x = x;
    this.y = y;
  }
  Position_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Position',
    interfaces: []
  };
  Position_0.prototype.component1 = function () {
    return this.x;
  };
  Position_0.prototype.component2 = function () {
    return this.y;
  };
  Position_0.prototype.copy_vux9f0$ = function (x, y) {
    return new Position_0(x === void 0 ? this.x : x, y === void 0 ? this.y : y);
  };
  Position_0.prototype.toString = function () {
    return 'Position(x=' + Kotlin.toString(this.x) + (', y=' + Kotlin.toString(this.y)) + ')';
  };
  Position_0.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.x) | 0;
    result = result * 31 + Kotlin.hashCode(this.y) | 0;
    return result;
  };
  Position_0.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.x, other.x) && Kotlin.equals(this.y, other.y)))));
  };
  function Tile() {
  }
  Tile.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Tile',
    interfaces: []
  };
  function UniqueTile() {
  }
  UniqueTile.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'UniqueTile',
    interfaces: [Tile]
  };
  function WalkableTile() {
  }
  WalkableTile.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'WalkableTile',
    interfaces: [Tile]
  };
  function StartTile(name, print, shapeOptions, position) {
    this.name = name;
    this.print_g087ho$_0 = print;
    this.shapeOptions_201rh2$_0 = shapeOptions;
    this.position_r9mtxy$_0 = position;
  }
  Object.defineProperty(StartTile.prototype, 'print', {
    get: function () {
      return this.print_g087ho$_0;
    }
  });
  Object.defineProperty(StartTile.prototype, 'shapeOptions', {
    get: function () {
      return this.shapeOptions_201rh2$_0;
    }
  });
  Object.defineProperty(StartTile.prototype, 'position', {
    get: function () {
      return this.position_r9mtxy$_0;
    }
  });
  StartTile.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'StartTile',
    interfaces: [UniqueTile]
  };
  function StartTile_init(position, $this) {
    $this = $this || Object.create(StartTile.prototype);
    StartTile.call($this, 'Start', '\u25A7', new ShapeOptions(void 0, void 0, void 0, void 0, '#ff0000'), position);
    return $this;
  }
  function StartTile_init_0(x, y, $this) {
    $this = $this || Object.create(StartTile.prototype);
    StartTile_init(new Position_0(x, y), $this);
    return $this;
  }
  StartTile.prototype.component1 = function () {
    return this.name;
  };
  StartTile.prototype.component2 = function () {
    return this.print;
  };
  StartTile.prototype.component3 = function () {
    return this.shapeOptions;
  };
  StartTile.prototype.component4 = function () {
    return this.position;
  };
  StartTile.prototype.copy_7z1i9v$ = function (name, print, shapeOptions, position) {
    return new StartTile(name === void 0 ? this.name : name, print === void 0 ? this.print : print, shapeOptions === void 0 ? this.shapeOptions : shapeOptions, position === void 0 ? this.position : position);
  };
  StartTile.prototype.toString = function () {
    return 'StartTile(name=' + Kotlin.toString(this.name) + (', print=' + Kotlin.toString(this.print)) + (', shapeOptions=' + Kotlin.toString(this.shapeOptions)) + (', position=' + Kotlin.toString(this.position)) + ')';
  };
  StartTile.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.name) | 0;
    result = result * 31 + Kotlin.hashCode(this.print) | 0;
    result = result * 31 + Kotlin.hashCode(this.shapeOptions) | 0;
    result = result * 31 + Kotlin.hashCode(this.position) | 0;
    return result;
  };
  StartTile.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.name, other.name) && Kotlin.equals(this.print, other.print) && Kotlin.equals(this.shapeOptions, other.shapeOptions) && Kotlin.equals(this.position, other.position)))));
  };
  function GoalTile(name, print, shapeOptions, position) {
    this.name = name;
    this.print_9qfkr5$_0 = print;
    this.shapeOptions_h14pa1$_0 = shapeOptions;
    this.position_vjv1fx$_0 = position;
  }
  Object.defineProperty(GoalTile.prototype, 'print', {
    get: function () {
      return this.print_9qfkr5$_0;
    }
  });
  Object.defineProperty(GoalTile.prototype, 'shapeOptions', {
    get: function () {
      return this.shapeOptions_h14pa1$_0;
    }
  });
  Object.defineProperty(GoalTile.prototype, 'position', {
    get: function () {
      return this.position_vjv1fx$_0;
    }
  });
  GoalTile.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'GoalTile',
    interfaces: [UniqueTile]
  };
  function GoalTile_init(position, $this) {
    $this = $this || Object.create(GoalTile.prototype);
    GoalTile.call($this, 'Goal', '\u25A8', new ShapeOptions(void 0, void 0, void 0, void 0, '#EE82EE'), position);
    return $this;
  }
  function GoalTile_init_0(x, y, $this) {
    $this = $this || Object.create(GoalTile.prototype);
    GoalTile_init(new Position_0(x, y), $this);
    return $this;
  }
  GoalTile.prototype.component1 = function () {
    return this.name;
  };
  GoalTile.prototype.component2 = function () {
    return this.print;
  };
  GoalTile.prototype.component3 = function () {
    return this.shapeOptions;
  };
  GoalTile.prototype.component4 = function () {
    return this.position;
  };
  GoalTile.prototype.copy_7z1i9v$ = function (name, print, shapeOptions, position) {
    return new GoalTile(name === void 0 ? this.name : name, print === void 0 ? this.print : print, shapeOptions === void 0 ? this.shapeOptions : shapeOptions, position === void 0 ? this.position : position);
  };
  GoalTile.prototype.toString = function () {
    return 'GoalTile(name=' + Kotlin.toString(this.name) + (', print=' + Kotlin.toString(this.print)) + (', shapeOptions=' + Kotlin.toString(this.shapeOptions)) + (', position=' + Kotlin.toString(this.position)) + ')';
  };
  GoalTile.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.name) | 0;
    result = result * 31 + Kotlin.hashCode(this.print) | 0;
    result = result * 31 + Kotlin.hashCode(this.shapeOptions) | 0;
    result = result * 31 + Kotlin.hashCode(this.position) | 0;
    return result;
  };
  GoalTile.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.name, other.name) && Kotlin.equals(this.print, other.print) && Kotlin.equals(this.shapeOptions, other.shapeOptions) && Kotlin.equals(this.position, other.position)))));
  };
  function WallTile(name, print, shapeOptions, position) {
    this.name = name;
    this.print_ow0k7a$_0 = print;
    this.shapeOptions_6ag840$_0 = shapeOptions;
    this.position_s2k5g$_0 = position;
  }
  Object.defineProperty(WallTile.prototype, 'print', {
    get: function () {
      return this.print_ow0k7a$_0;
    }
  });
  Object.defineProperty(WallTile.prototype, 'shapeOptions', {
    get: function () {
      return this.shapeOptions_6ag840$_0;
    }
  });
  Object.defineProperty(WallTile.prototype, 'position', {
    get: function () {
      return this.position_s2k5g$_0;
    }
  });
  WallTile.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'WallTile',
    interfaces: [Tile]
  };
  function WallTile_init(position, $this) {
    $this = $this || Object.create(WallTile.prototype);
    WallTile.call($this, 'Wall', '\u25A0', new ShapeOptions(void 0, void 0, void 0, void 0, '#000000'), position);
    return $this;
  }
  function WallTile_init_0(x, y, $this) {
    $this = $this || Object.create(WallTile.prototype);
    WallTile_init(new Position_0(x, y), $this);
    return $this;
  }
  WallTile.prototype.component1 = function () {
    return this.name;
  };
  WallTile.prototype.component2 = function () {
    return this.print;
  };
  WallTile.prototype.component3 = function () {
    return this.shapeOptions;
  };
  WallTile.prototype.component4 = function () {
    return this.position;
  };
  WallTile.prototype.copy_7z1i9v$ = function (name, print, shapeOptions, position) {
    return new WallTile(name === void 0 ? this.name : name, print === void 0 ? this.print : print, shapeOptions === void 0 ? this.shapeOptions : shapeOptions, position === void 0 ? this.position : position);
  };
  WallTile.prototype.toString = function () {
    return 'WallTile(name=' + Kotlin.toString(this.name) + (', print=' + Kotlin.toString(this.print)) + (', shapeOptions=' + Kotlin.toString(this.shapeOptions)) + (', position=' + Kotlin.toString(this.position)) + ')';
  };
  WallTile.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.name) | 0;
    result = result * 31 + Kotlin.hashCode(this.print) | 0;
    result = result * 31 + Kotlin.hashCode(this.shapeOptions) | 0;
    result = result * 31 + Kotlin.hashCode(this.position) | 0;
    return result;
  };
  WallTile.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.name, other.name) && Kotlin.equals(this.print, other.print) && Kotlin.equals(this.shapeOptions, other.shapeOptions) && Kotlin.equals(this.position, other.position)))));
  };
  function EmptyTile(name, print, shapeOptions, position) {
    this.name = name;
    this.print_gbks3t$_0 = print;
    this.shapeOptions_a5lo5b$_0 = shapeOptions;
    this.position_gvaaol$_0 = position;
  }
  Object.defineProperty(EmptyTile.prototype, 'print', {
    get: function () {
      return this.print_gbks3t$_0;
    }
  });
  Object.defineProperty(EmptyTile.prototype, 'shapeOptions', {
    get: function () {
      return this.shapeOptions_a5lo5b$_0;
    }
  });
  Object.defineProperty(EmptyTile.prototype, 'position', {
    get: function () {
      return this.position_gvaaol$_0;
    }
  });
  EmptyTile.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EmptyTile',
    interfaces: [WalkableTile]
  };
  function EmptyTile_init(position, $this) {
    $this = $this || Object.create(EmptyTile.prototype);
    EmptyTile.call($this, 'Empty', '\u25A1', new ShapeOptions(void 0, void 0, void 0, void 0, '#F5F5F5'), position);
    return $this;
  }
  function EmptyTile_init_0(x, y, $this) {
    $this = $this || Object.create(EmptyTile.prototype);
    EmptyTile_init(new Position_0(x, y), $this);
    return $this;
  }
  EmptyTile.prototype.component1 = function () {
    return this.name;
  };
  EmptyTile.prototype.component2 = function () {
    return this.print;
  };
  EmptyTile.prototype.component3 = function () {
    return this.shapeOptions;
  };
  EmptyTile.prototype.component4 = function () {
    return this.position;
  };
  EmptyTile.prototype.copy_7z1i9v$ = function (name, print, shapeOptions, position) {
    return new EmptyTile(name === void 0 ? this.name : name, print === void 0 ? this.print : print, shapeOptions === void 0 ? this.shapeOptions : shapeOptions, position === void 0 ? this.position : position);
  };
  EmptyTile.prototype.toString = function () {
    return 'EmptyTile(name=' + Kotlin.toString(this.name) + (', print=' + Kotlin.toString(this.print)) + (', shapeOptions=' + Kotlin.toString(this.shapeOptions)) + (', position=' + Kotlin.toString(this.position)) + ')';
  };
  EmptyTile.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.name) | 0;
    result = result * 31 + Kotlin.hashCode(this.print) | 0;
    result = result * 31 + Kotlin.hashCode(this.shapeOptions) | 0;
    result = result * 31 + Kotlin.hashCode(this.position) | 0;
    return result;
  };
  EmptyTile.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.name, other.name) && Kotlin.equals(this.print, other.print) && Kotlin.equals(this.shapeOptions, other.shapeOptions) && Kotlin.equals(this.position, other.position)))));
  };
  function WatchedTile(name, print, shapeOptions, position) {
    this.name = name;
    this.print_sv78co$_0 = print;
    this.shapeOptions_1by02$_0 = shapeOptions;
    this.position_gncakq$_0 = position;
  }
  Object.defineProperty(WatchedTile.prototype, 'print', {
    get: function () {
      return this.print_sv78co$_0;
    }
  });
  Object.defineProperty(WatchedTile.prototype, 'shapeOptions', {
    get: function () {
      return this.shapeOptions_1by02$_0;
    }
  });
  Object.defineProperty(WatchedTile.prototype, 'position', {
    get: function () {
      return this.position_gncakq$_0;
    }
  });
  WatchedTile.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'WatchedTile',
    interfaces: [WalkableTile]
  };
  function WatchedTile_init(position, $this) {
    $this = $this || Object.create(WatchedTile.prototype);
    WatchedTile.call($this, 'Watched', '\u25A9', new ShapeOptions(void 0, void 0, void 0, void 0, '#00ff00'), position);
    return $this;
  }
  function WatchedTile_init_0(x, y, $this) {
    $this = $this || Object.create(WatchedTile.prototype);
    WatchedTile_init(new Position_0(x, y), $this);
    return $this;
  }
  WatchedTile.prototype.component1 = function () {
    return this.name;
  };
  WatchedTile.prototype.component2 = function () {
    return this.print;
  };
  WatchedTile.prototype.component3 = function () {
    return this.shapeOptions;
  };
  WatchedTile.prototype.component4 = function () {
    return this.position;
  };
  WatchedTile.prototype.copy_7z1i9v$ = function (name, print, shapeOptions, position) {
    return new WatchedTile(name === void 0 ? this.name : name, print === void 0 ? this.print : print, shapeOptions === void 0 ? this.shapeOptions : shapeOptions, position === void 0 ? this.position : position);
  };
  WatchedTile.prototype.toString = function () {
    return 'WatchedTile(name=' + Kotlin.toString(this.name) + (', print=' + Kotlin.toString(this.print)) + (', shapeOptions=' + Kotlin.toString(this.shapeOptions)) + (', position=' + Kotlin.toString(this.position)) + ')';
  };
  WatchedTile.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.name) | 0;
    result = result * 31 + Kotlin.hashCode(this.print) | 0;
    result = result * 31 + Kotlin.hashCode(this.shapeOptions) | 0;
    result = result * 31 + Kotlin.hashCode(this.position) | 0;
    return result;
  };
  WatchedTile.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.name, other.name) && Kotlin.equals(this.print, other.print) && Kotlin.equals(this.shapeOptions, other.shapeOptions) && Kotlin.equals(this.position, other.position)))));
  };
  function VisitedTile(name, print, shapeOptions, position) {
    this.name = name;
    this.print_20gv78$_0 = print;
    this.shapeOptions_daf6aq$_0 = shapeOptions;
    this.position_woxfym$_0 = position;
  }
  Object.defineProperty(VisitedTile.prototype, 'print', {
    get: function () {
      return this.print_20gv78$_0;
    }
  });
  Object.defineProperty(VisitedTile.prototype, 'shapeOptions', {
    get: function () {
      return this.shapeOptions_daf6aq$_0;
    }
  });
  Object.defineProperty(VisitedTile.prototype, 'position', {
    get: function () {
      return this.position_woxfym$_0;
    }
  });
  VisitedTile.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'VisitedTile',
    interfaces: [WalkableTile]
  };
  function VisitedTile_init(position, $this) {
    $this = $this || Object.create(VisitedTile.prototype);
    VisitedTile.call($this, 'VisitedTile', '\u25A9', new ShapeOptions(void 0, void 0, void 0, void 0, '#0000FF'), position);
    return $this;
  }
  function VisitedTile_init_0(x, y, $this) {
    $this = $this || Object.create(VisitedTile.prototype);
    VisitedTile_init(new Position_0(x, y), $this);
    return $this;
  }
  VisitedTile.prototype.component1 = function () {
    return this.name;
  };
  VisitedTile.prototype.component2 = function () {
    return this.print;
  };
  VisitedTile.prototype.component3 = function () {
    return this.shapeOptions;
  };
  VisitedTile.prototype.component4 = function () {
    return this.position;
  };
  VisitedTile.prototype.copy_7z1i9v$ = function (name, print, shapeOptions, position) {
    return new VisitedTile(name === void 0 ? this.name : name, print === void 0 ? this.print : print, shapeOptions === void 0 ? this.shapeOptions : shapeOptions, position === void 0 ? this.position : position);
  };
  VisitedTile.prototype.toString = function () {
    return 'VisitedTile(name=' + Kotlin.toString(this.name) + (', print=' + Kotlin.toString(this.print)) + (', shapeOptions=' + Kotlin.toString(this.shapeOptions)) + (', position=' + Kotlin.toString(this.position)) + ')';
  };
  VisitedTile.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.name) | 0;
    result = result * 31 + Kotlin.hashCode(this.print) | 0;
    result = result * 31 + Kotlin.hashCode(this.shapeOptions) | 0;
    result = result * 31 + Kotlin.hashCode(this.position) | 0;
    return result;
  };
  VisitedTile.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.name, other.name) && Kotlin.equals(this.print, other.print) && Kotlin.equals(this.shapeOptions, other.shapeOptions) && Kotlin.equals(this.position, other.position)))));
  };
  function PathTile(name, print, shapeOptions, position) {
    this.name = name;
    this.print_xogsch$_0 = print;
    this.shapeOptions_ibxmdn$_0 = shapeOptions;
    this.position_jyp8kf$_0 = position;
  }
  Object.defineProperty(PathTile.prototype, 'print', {
    get: function () {
      return this.print_xogsch$_0;
    }
  });
  Object.defineProperty(PathTile.prototype, 'shapeOptions', {
    get: function () {
      return this.shapeOptions_ibxmdn$_0;
    }
  });
  Object.defineProperty(PathTile.prototype, 'position', {
    get: function () {
      return this.position_jyp8kf$_0;
    }
  });
  PathTile.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PathTile',
    interfaces: [WalkableTile]
  };
  function PathTile_init(position, $this) {
    $this = $this || Object.create(PathTile.prototype);
    PathTile.call($this, 'Path', '\u25A3', new ShapeOptions(void 0, void 0, void 0, void 0, '#FFA500'), position);
    return $this;
  }
  function PathTile_init_0(x, y, $this) {
    $this = $this || Object.create(PathTile.prototype);
    PathTile_init(new Position_0(x, y), $this);
    return $this;
  }
  PathTile.prototype.component1 = function () {
    return this.name;
  };
  PathTile.prototype.component2 = function () {
    return this.print;
  };
  PathTile.prototype.component3 = function () {
    return this.shapeOptions;
  };
  PathTile.prototype.component4 = function () {
    return this.position;
  };
  PathTile.prototype.copy_7z1i9v$ = function (name, print, shapeOptions, position) {
    return new PathTile(name === void 0 ? this.name : name, print === void 0 ? this.print : print, shapeOptions === void 0 ? this.shapeOptions : shapeOptions, position === void 0 ? this.position : position);
  };
  PathTile.prototype.toString = function () {
    return 'PathTile(name=' + Kotlin.toString(this.name) + (', print=' + Kotlin.toString(this.print)) + (', shapeOptions=' + Kotlin.toString(this.shapeOptions)) + (', position=' + Kotlin.toString(this.position)) + ')';
  };
  PathTile.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.name) | 0;
    result = result * 31 + Kotlin.hashCode(this.print) | 0;
    result = result * 31 + Kotlin.hashCode(this.shapeOptions) | 0;
    result = result * 31 + Kotlin.hashCode(this.position) | 0;
    return result;
  };
  PathTile.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.name, other.name) && Kotlin.equals(this.print, other.print) && Kotlin.equals(this.shapeOptions, other.shapeOptions) && Kotlin.equals(this.position, other.position)))));
  };
  function BoardUi() {
  }
  BoardUi.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'BoardUi',
    interfaces: []
  };
  function ConsoleUi() {
    ConsoleUi_instance = this;
  }
  function ConsoleUi$drawTiles$lambda$lambda(it) {
    return it.print;
  }
  ConsoleUi.prototype.drawTiles_3atklz$ = function (tiles) {
    var tmp$;
    var array = Array_0(tiles[0].length);
    var tmp$_0;
    tmp$_0 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_0; i++) {
      var array_0 = Array_0(tiles.length);
      var tmp$_1;
      tmp$_1 = array_0.length - 1 | 0;
      for (var i_0 = 0; i_0 <= tmp$_1; i_0++) {
        var tmp$_2;
        array_0[i_0] = Kotlin.isType(tmp$_2 = EmptyTile_init_0(i, i_0), Tile) ? tmp$_2 : throwCCE();
      }
      array[i] = array_0;
    }
    var rotated = array;
    tmp$ = tiles[0].length;
    for (var i_1 = 0; i_1 < tmp$; i_1++) {
      for (var j = tiles.length - 1 | 0; j >= 0; j--) {
        rotated[i_1][j] = tiles[j][i_1];
      }
    }
    var tmp$_3;
    for (tmp$_3 = 0; tmp$_3 !== rotated.length; ++tmp$_3) {
      var element = rotated[tmp$_3];
      console.log(joinToString(element, '', void 0, void 0, void 0, void 0, ConsoleUi$drawTiles$lambda$lambda));
    }
    console.log();
  };
  ConsoleUi.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ConsoleUi',
    interfaces: [BoardUi]
  };
  var ConsoleUi_instance = null;
  function ConsoleUi_getInstance() {
    if (ConsoleUi_instance === null) {
      new ConsoleUi();
    }
    return ConsoleUi_instance;
  }
  function ShapeOptions(roughness, bowing, stroke, strokeWidth, fill, fillStyle, fillWeight, hachureAngle, hachureGap, curveStepCount, simplification) {
    if (roughness === void 0)
      roughness = 1.0;
    if (bowing === void 0)
      bowing = 1;
    if (stroke === void 0)
      stroke = '#000000';
    if (strokeWidth === void 0)
      strokeWidth = 1;
    if (fill === void 0)
      fill = 'White';
    if (fillStyle === void 0)
      fillStyle = 'hachure';
    if (fillWeight === void 0)
      fillWeight = strokeWidth / 2;
    if (hachureAngle === void 0)
      hachureAngle = -41;
    if (hachureGap === void 0)
      hachureGap = strokeWidth * 4 | 0;
    if (curveStepCount === void 0)
      curveStepCount = 9;
    if (simplification === void 0)
      simplification = 0.0;
    this.roughness = roughness;
    this.bowing = bowing;
    this.stroke = stroke;
    this.strokeWidth = strokeWidth;
    this.fill = fill;
    this.fillStyle = fillStyle;
    this.fillWeight = fillWeight;
    this.hachureAngle = hachureAngle;
    this.hachureGap = hachureGap;
    this.curveStepCount = curveStepCount;
    this.simplification = simplification;
  }
  ShapeOptions.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ShapeOptions',
    interfaces: []
  };
  ShapeOptions.prototype.component1 = function () {
    return this.roughness;
  };
  ShapeOptions.prototype.component2 = function () {
    return this.bowing;
  };
  ShapeOptions.prototype.component3 = function () {
    return this.stroke;
  };
  ShapeOptions.prototype.component4 = function () {
    return this.strokeWidth;
  };
  ShapeOptions.prototype.component5 = function () {
    return this.fill;
  };
  ShapeOptions.prototype.component6 = function () {
    return this.fillStyle;
  };
  ShapeOptions.prototype.component7 = function () {
    return this.fillWeight;
  };
  ShapeOptions.prototype.component8 = function () {
    return this.hachureAngle;
  };
  ShapeOptions.prototype.component9 = function () {
    return this.hachureGap;
  };
  ShapeOptions.prototype.component10 = function () {
    return this.curveStepCount;
  };
  ShapeOptions.prototype.component11 = function () {
    return this.simplification;
  };
  ShapeOptions.prototype.copy_sg6bk2$ = function (roughness, bowing, stroke, strokeWidth, fill, fillStyle, fillWeight, hachureAngle, hachureGap, curveStepCount, simplification) {
    return new ShapeOptions(roughness === void 0 ? this.roughness : roughness, bowing === void 0 ? this.bowing : bowing, stroke === void 0 ? this.stroke : stroke, strokeWidth === void 0 ? this.strokeWidth : strokeWidth, fill === void 0 ? this.fill : fill, fillStyle === void 0 ? this.fillStyle : fillStyle, fillWeight === void 0 ? this.fillWeight : fillWeight, hachureAngle === void 0 ? this.hachureAngle : hachureAngle, hachureGap === void 0 ? this.hachureGap : hachureGap, curveStepCount === void 0 ? this.curveStepCount : curveStepCount, simplification === void 0 ? this.simplification : simplification);
  };
  ShapeOptions.prototype.toString = function () {
    return 'ShapeOptions(roughness=' + Kotlin.toString(this.roughness) + (', bowing=' + Kotlin.toString(this.bowing)) + (', stroke=' + Kotlin.toString(this.stroke)) + (', strokeWidth=' + Kotlin.toString(this.strokeWidth)) + (', fill=' + Kotlin.toString(this.fill)) + (', fillStyle=' + Kotlin.toString(this.fillStyle)) + (', fillWeight=' + Kotlin.toString(this.fillWeight)) + (', hachureAngle=' + Kotlin.toString(this.hachureAngle)) + (', hachureGap=' + Kotlin.toString(this.hachureGap)) + (', curveStepCount=' + Kotlin.toString(this.curveStepCount)) + (', simplification=' + Kotlin.toString(this.simplification)) + ')';
  };
  ShapeOptions.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.roughness) | 0;
    result = result * 31 + Kotlin.hashCode(this.bowing) | 0;
    result = result * 31 + Kotlin.hashCode(this.stroke) | 0;
    result = result * 31 + Kotlin.hashCode(this.strokeWidth) | 0;
    result = result * 31 + Kotlin.hashCode(this.fill) | 0;
    result = result * 31 + Kotlin.hashCode(this.fillStyle) | 0;
    result = result * 31 + Kotlin.hashCode(this.fillWeight) | 0;
    result = result * 31 + Kotlin.hashCode(this.hachureAngle) | 0;
    result = result * 31 + Kotlin.hashCode(this.hachureGap) | 0;
    result = result * 31 + Kotlin.hashCode(this.curveStepCount) | 0;
    result = result * 31 + Kotlin.hashCode(this.simplification) | 0;
    return result;
  };
  ShapeOptions.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.roughness, other.roughness) && Kotlin.equals(this.bowing, other.bowing) && Kotlin.equals(this.stroke, other.stroke) && Kotlin.equals(this.strokeWidth, other.strokeWidth) && Kotlin.equals(this.fill, other.fill) && Kotlin.equals(this.fillStyle, other.fillStyle) && Kotlin.equals(this.fillWeight, other.fillWeight) && Kotlin.equals(this.hachureAngle, other.hachureAngle) && Kotlin.equals(this.hachureGap, other.hachureGap) && Kotlin.equals(this.curveStepCount, other.curveStepCount) && Kotlin.equals(this.simplification, other.simplification)))));
  };
  function RoughJsUi() {
    RoughJsUi_instance = this;
    this.maze = null;
    this.legend = null;
    this.mazeCanvas = null;
    var tmp$, tmp$_0;
    this.mazeCanvas = Kotlin.isType(tmp$ = document.getElementById('maze'), HTMLCanvasElement) ? tmp$ : throwCCE();
    this.maze = rough.canvas(this.mazeCanvas);
    this.legend = rough.canvas(Kotlin.isType(tmp$_0 = document.getElementById('legend'), HTMLCanvasElement) ? tmp$_0 : throwCCE());
    this.drawLegend_0();
  }
  RoughJsUi.prototype.drawLegend_0 = function () {
    var tileSize = 80;
    var yOffset = 40;
    var yOffsetForItems = yOffset + tileSize | 0;
    var gap = 90;
    var kindOfTiles = listOf([EmptyTile_init_0(-1, -1), StartTile_init_0(-1, -1), GoalTile_init_0(-1, -1), WatchedTile_init_0(-1, -1), VisitedTile_init_0(-1, -1), WallTile_init_0(-1, -1)]);
    this.legend.rectangle(0, yOffset, tileSize * 3 | 0, Kotlin.imul(tileSize, kindOfTiles.size + 1 | 0) * 2 | 0);
    var tmp$, tmp$_0;
    var index = 0;
    tmp$ = kindOfTiles.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      var index_0 = (tmp$_0 = index, index = tmp$_0 + 1 | 0, tmp$_0);
      this.legend.rectangle(tileSize, yOffsetForItems + Kotlin.imul(index_0, tileSize) + Kotlin.imul(gap, index_0) | 0, tileSize, tileSize, item.shapeOptions);
    }
  };
  RoughJsUi.prototype.drawTiles_3atklz$ = function (tiles) {
    var tmp$;
    var xOffset = 40;
    var yOffset = 40;
    var tileSize = 80;
    var ctx = ensureNotNull((tmp$ = this.mazeCanvas.getContext('2d')) == null || Kotlin.isType(tmp$, CanvasRenderingContext2D) ? tmp$ : throwCCE());
    ctx.clearRect(0.0, 0.0, 1400.0, 1400.0);
    var tmp$_0, tmp$_0_0;
    var index = 0;
    for (tmp$_0 = 0; tmp$_0 !== tiles.length; ++tmp$_0) {
      var item = tiles[tmp$_0];
      var columnIndex = (tmp$_0_0 = index, index = tmp$_0_0 + 1 | 0, tmp$_0_0);
      var tmp$_1, tmp$_0_1;
      var index_0 = 0;
      for (tmp$_1 = 0; tmp$_1 !== item.length; ++tmp$_1) {
        var item_0 = item[tmp$_1];
        var rowIndex = (tmp$_0_1 = index_0, index_0 = tmp$_0_1 + 1 | 0, tmp$_0_1);
        this.maze.rectangle(Kotlin.imul(tileSize, columnIndex) + xOffset | 0, Kotlin.imul(tileSize, rowIndex) + yOffset | 0, tileSize, tileSize, item_0.shapeOptions);
      }
    }
  };
  RoughJsUi.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'RoughJsUi',
    interfaces: [BoardUi]
  };
  var RoughJsUi_instance = null;
  function RoughJsUi_getInstance() {
    if (RoughJsUi_instance === null) {
      new RoughJsUi();
    }
    return RoughJsUi_instance;
  }
  Object.defineProperty(BreathFirstSearch$Entry, 'Factory', {
    get: BreathFirstSearch$Entry$Factory_getInstance
  });
  BreathFirstSearch.Entry = BreathFirstSearch$Entry;
  var package$algorithm = _.algorithm || (_.algorithm = {});
  package$algorithm.BreathFirstSearch = BreathFirstSearch;
  package$algorithm.DepthFirstSearchFail = DepthFirstSearchFail;
  PathFindingAlgorithm.PathFindingResult = PathFindingAlgorithm$PathFindingResult;
  package$algorithm.PathFindingAlgorithm = PathFindingAlgorithm;
  package$algorithm.Queue = Queue;
  Board.BoardSize = Board$BoardSize;
  Board.NeighbourTiles = Board$NeighbourTiles;
  Board.BoardInitException = Board$BoardInitException;
  Board.BoardAccessException = Board$BoardAccessException;
  Board.BoardPathException = Board$BoardPathException;
  Board.BoardSetException = Board$BoardSetException;
  Object.defineProperty(Board, 'Factory', {
    get: Board$Factory_getInstance
  });
  $$importsForInline$$.pathalgorithm2 = _;
  var package$board = _.board || (_.board = {});
  package$board.Board = Board;
  package$board.angleLevelWithGoalOn_x13_y3_y5ntp1$ = angleLevelWithGoalOn_x13_y3;
  package$board.weirdLevel_y5ntp1$ = weirdLevel;
  Object.defineProperty(_, 'Bootstrap', {
    get: Bootstrap_getInstance
  });
  var package$delegates = _.delegates || (_.delegates = {});
  package$delegates.NotNegative = NotNegative;
  Object.defineProperty(Path, 'Factory', {
    get: Path$Factory_getInstance
  });
  var package$tiles = _.tiles || (_.tiles = {});
  package$tiles.Path_init_in2pv6$ = Path_init;
  package$tiles.Path_init_vux9f0$ = Path_init_0;
  package$tiles.Path = Path;
  package$tiles.Position = Position_0;
  package$tiles.Tile = Tile;
  package$tiles.UniqueTile = UniqueTile;
  package$tiles.WalkableTile = WalkableTile;
  package$tiles.StartTile_init_j3yxri$ = StartTile_init;
  package$tiles.StartTile_init_vux9f0$ = StartTile_init_0;
  package$tiles.StartTile = StartTile;
  package$tiles.GoalTile_init_j3yxri$ = GoalTile_init;
  package$tiles.GoalTile_init_vux9f0$ = GoalTile_init_0;
  package$tiles.GoalTile = GoalTile;
  package$tiles.WallTile_init_j3yxri$ = WallTile_init;
  package$tiles.WallTile_init_vux9f0$ = WallTile_init_0;
  package$tiles.WallTile = WallTile;
  package$tiles.EmptyTile_init_j3yxri$ = EmptyTile_init;
  package$tiles.EmptyTile_init_vux9f0$ = EmptyTile_init_0;
  package$tiles.EmptyTile = EmptyTile;
  package$tiles.WatchedTile_init_j3yxri$ = WatchedTile_init;
  package$tiles.WatchedTile_init_vux9f0$ = WatchedTile_init_0;
  package$tiles.WatchedTile = WatchedTile;
  package$tiles.VisitedTile_init_j3yxri$ = VisitedTile_init;
  package$tiles.VisitedTile_init_vux9f0$ = VisitedTile_init_0;
  package$tiles.VisitedTile = VisitedTile;
  package$tiles.PathTile_init_j3yxri$ = PathTile_init;
  package$tiles.PathTile_init_vux9f0$ = PathTile_init_0;
  package$tiles.PathTile = PathTile;
  var package$ui = _.ui || (_.ui = {});
  package$ui.BoardUi = BoardUi;
  Object.defineProperty(package$ui, 'ConsoleUi', {
    get: ConsoleUi_getInstance
  });
  package$ui.ShapeOptions = ShapeOptions;
  Object.defineProperty(package$ui, 'RoughJsUi', {
    get: RoughJsUi_getInstance
  });
  BreathFirstSearch.prototype.solveWithoutDelay_odmita$ = PathFindingAlgorithm.prototype.solveWithoutDelay_odmita$;
  BreathFirstSearch.prototype.solve_kzwmta$ = PathFindingAlgorithm.prototype.solve_kzwmta$;
  Kotlin.defineModule('pathalgorithm2', _);
  return _;
}));

//# sourceMappingURL=pathalgorithm2.js.map
