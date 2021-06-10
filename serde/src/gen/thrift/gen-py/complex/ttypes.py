#
# Autogenerated by Thrift Compiler (0.14.1)
#
# DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
#
#  options string: py
#

from thrift.Thrift import TType, TMessageType, TFrozenDict, TException, TApplicationException
from thrift.protocol.TProtocol import TProtocolException
from thrift.TRecursive import fix_spec

import sys

from thrift.transport import TTransport
all_structs = []


class PropValueUnion(object):
    """
    Attributes:
     - intValue
     - longValue
     - stringValue
     - doubleValue
     - flag
     - lString
     - unionMStringString

    """


    def __init__(self, intValue=None, longValue=None, stringValue=None, doubleValue=None, flag=None, lString=None, unionMStringString=None,):
        self.intValue = intValue
        self.longValue = longValue
        self.stringValue = stringValue
        self.doubleValue = doubleValue
        self.flag = flag
        self.lString = lString
        self.unionMStringString = unionMStringString

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 1:
                if ftype == TType.I32:
                    self.intValue = iprot.readI32()
                else:
                    iprot.skip(ftype)
            elif fid == 2:
                if ftype == TType.I64:
                    self.longValue = iprot.readI64()
                else:
                    iprot.skip(ftype)
            elif fid == 3:
                if ftype == TType.STRING:
                    self.stringValue = iprot.readString().decode('utf-8', errors='replace') if sys.version_info[0] == 2 else iprot.readString()
                else:
                    iprot.skip(ftype)
            elif fid == 4:
                if ftype == TType.DOUBLE:
                    self.doubleValue = iprot.readDouble()
                else:
                    iprot.skip(ftype)
            elif fid == 5:
                if ftype == TType.BOOL:
                    self.flag = iprot.readBool()
                else:
                    iprot.skip(ftype)
            elif fid == 6:
                if ftype == TType.LIST:
                    self.lString = []
                    (_etype3, _size0) = iprot.readListBegin()
                    for _i4 in range(_size0):
                        _elem5 = iprot.readString().decode('utf-8', errors='replace') if sys.version_info[0] == 2 else iprot.readString()
                        self.lString.append(_elem5)
                    iprot.readListEnd()
                else:
                    iprot.skip(ftype)
            elif fid == 7:
                if ftype == TType.MAP:
                    self.unionMStringString = {}
                    (_ktype7, _vtype8, _size6) = iprot.readMapBegin()
                    for _i10 in range(_size6):
                        _key11 = iprot.readString().decode('utf-8', errors='replace') if sys.version_info[0] == 2 else iprot.readString()
                        _val12 = iprot.readString().decode('utf-8', errors='replace') if sys.version_info[0] == 2 else iprot.readString()
                        self.unionMStringString[_key11] = _val12
                    iprot.readMapEnd()
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('PropValueUnion')
        if self.intValue is not None:
            oprot.writeFieldBegin('intValue', TType.I32, 1)
            oprot.writeI32(self.intValue)
            oprot.writeFieldEnd()
        if self.longValue is not None:
            oprot.writeFieldBegin('longValue', TType.I64, 2)
            oprot.writeI64(self.longValue)
            oprot.writeFieldEnd()
        if self.stringValue is not None:
            oprot.writeFieldBegin('stringValue', TType.STRING, 3)
            oprot.writeString(self.stringValue.encode('utf-8') if sys.version_info[0] == 2 else self.stringValue)
            oprot.writeFieldEnd()
        if self.doubleValue is not None:
            oprot.writeFieldBegin('doubleValue', TType.DOUBLE, 4)
            oprot.writeDouble(self.doubleValue)
            oprot.writeFieldEnd()
        if self.flag is not None:
            oprot.writeFieldBegin('flag', TType.BOOL, 5)
            oprot.writeBool(self.flag)
            oprot.writeFieldEnd()
        if self.lString is not None:
            oprot.writeFieldBegin('lString', TType.LIST, 6)
            oprot.writeListBegin(TType.STRING, len(self.lString))
            for iter13 in self.lString:
                oprot.writeString(iter13.encode('utf-8') if sys.version_info[0] == 2 else iter13)
            oprot.writeListEnd()
            oprot.writeFieldEnd()
        if self.unionMStringString is not None:
            oprot.writeFieldBegin('unionMStringString', TType.MAP, 7)
            oprot.writeMapBegin(TType.STRING, TType.STRING, len(self.unionMStringString))
            for kiter14, viter15 in self.unionMStringString.items():
                oprot.writeString(kiter14.encode('utf-8') if sys.version_info[0] == 2 else kiter14)
                oprot.writeString(viter15.encode('utf-8') if sys.version_info[0] == 2 else viter15)
            oprot.writeMapEnd()
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        return

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)


class IntString(object):
    """
    Attributes:
     - myint
     - myString
     - underscore_int

    """


    def __init__(self, myint=None, myString=None, underscore_int=None,):
        self.myint = myint
        self.myString = myString
        self.underscore_int = underscore_int

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 1:
                if ftype == TType.I32:
                    self.myint = iprot.readI32()
                else:
                    iprot.skip(ftype)
            elif fid == 2:
                if ftype == TType.STRING:
                    self.myString = iprot.readString().decode('utf-8', errors='replace') if sys.version_info[0] == 2 else iprot.readString()
                else:
                    iprot.skip(ftype)
            elif fid == 3:
                if ftype == TType.I32:
                    self.underscore_int = iprot.readI32()
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('IntString')
        if self.myint is not None:
            oprot.writeFieldBegin('myint', TType.I32, 1)
            oprot.writeI32(self.myint)
            oprot.writeFieldEnd()
        if self.myString is not None:
            oprot.writeFieldBegin('myString', TType.STRING, 2)
            oprot.writeString(self.myString.encode('utf-8') if sys.version_info[0] == 2 else self.myString)
            oprot.writeFieldEnd()
        if self.underscore_int is not None:
            oprot.writeFieldBegin('underscore_int', TType.I32, 3)
            oprot.writeI32(self.underscore_int)
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        return

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)


class Complex(object):
    """
    Attributes:
     - aint
     - aString
     - lint
     - lString
     - lintString
     - mStringString
     - attributes
     - unionField1
     - unionField2
     - unionField3

    """


    def __init__(self, aint=None, aString=None, lint=None, lString=None, lintString=None, mStringString=None, attributes=None, unionField1=None, unionField2=None, unionField3=None,):
        self.aint = aint
        self.aString = aString
        self.lint = lint
        self.lString = lString
        self.lintString = lintString
        self.mStringString = mStringString
        self.attributes = attributes
        self.unionField1 = unionField1
        self.unionField2 = unionField2
        self.unionField3 = unionField3

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 1:
                if ftype == TType.I32:
                    self.aint = iprot.readI32()
                else:
                    iprot.skip(ftype)
            elif fid == 2:
                if ftype == TType.STRING:
                    self.aString = iprot.readString().decode('utf-8', errors='replace') if sys.version_info[0] == 2 else iprot.readString()
                else:
                    iprot.skip(ftype)
            elif fid == 3:
                if ftype == TType.LIST:
                    self.lint = []
                    (_etype19, _size16) = iprot.readListBegin()
                    for _i20 in range(_size16):
                        _elem21 = iprot.readI32()
                        self.lint.append(_elem21)
                    iprot.readListEnd()
                else:
                    iprot.skip(ftype)
            elif fid == 4:
                if ftype == TType.LIST:
                    self.lString = []
                    (_etype25, _size22) = iprot.readListBegin()
                    for _i26 in range(_size22):
                        _elem27 = iprot.readString().decode('utf-8', errors='replace') if sys.version_info[0] == 2 else iprot.readString()
                        self.lString.append(_elem27)
                    iprot.readListEnd()
                else:
                    iprot.skip(ftype)
            elif fid == 5:
                if ftype == TType.LIST:
                    self.lintString = []
                    (_etype31, _size28) = iprot.readListBegin()
                    for _i32 in range(_size28):
                        _elem33 = IntString()
                        _elem33.read(iprot)
                        self.lintString.append(_elem33)
                    iprot.readListEnd()
                else:
                    iprot.skip(ftype)
            elif fid == 6:
                if ftype == TType.MAP:
                    self.mStringString = {}
                    (_ktype35, _vtype36, _size34) = iprot.readMapBegin()
                    for _i38 in range(_size34):
                        _key39 = iprot.readString().decode('utf-8', errors='replace') if sys.version_info[0] == 2 else iprot.readString()
                        _val40 = iprot.readString().decode('utf-8', errors='replace') if sys.version_info[0] == 2 else iprot.readString()
                        self.mStringString[_key39] = _val40
                    iprot.readMapEnd()
                else:
                    iprot.skip(ftype)
            elif fid == 7:
                if ftype == TType.MAP:
                    self.attributes = {}
                    (_ktype42, _vtype43, _size41) = iprot.readMapBegin()
                    for _i45 in range(_size41):
                        _key46 = iprot.readString().decode('utf-8', errors='replace') if sys.version_info[0] == 2 else iprot.readString()
                        _val47 = {}
                        (_ktype49, _vtype50, _size48) = iprot.readMapBegin()
                        for _i52 in range(_size48):
                            _key53 = iprot.readString().decode('utf-8', errors='replace') if sys.version_info[0] == 2 else iprot.readString()
                            _val54 = {}
                            (_ktype56, _vtype57, _size55) = iprot.readMapBegin()
                            for _i59 in range(_size55):
                                _key60 = iprot.readString().decode('utf-8', errors='replace') if sys.version_info[0] == 2 else iprot.readString()
                                _val61 = PropValueUnion()
                                _val61.read(iprot)
                                _val54[_key60] = _val61
                            iprot.readMapEnd()
                            _val47[_key53] = _val54
                        iprot.readMapEnd()
                        self.attributes[_key46] = _val47
                    iprot.readMapEnd()
                else:
                    iprot.skip(ftype)
            elif fid == 8:
                if ftype == TType.STRUCT:
                    self.unionField1 = PropValueUnion()
                    self.unionField1.read(iprot)
                else:
                    iprot.skip(ftype)
            elif fid == 9:
                if ftype == TType.STRUCT:
                    self.unionField2 = PropValueUnion()
                    self.unionField2.read(iprot)
                else:
                    iprot.skip(ftype)
            elif fid == 10:
                if ftype == TType.STRUCT:
                    self.unionField3 = PropValueUnion()
                    self.unionField3.read(iprot)
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('Complex')
        if self.aint is not None:
            oprot.writeFieldBegin('aint', TType.I32, 1)
            oprot.writeI32(self.aint)
            oprot.writeFieldEnd()
        if self.aString is not None:
            oprot.writeFieldBegin('aString', TType.STRING, 2)
            oprot.writeString(self.aString.encode('utf-8') if sys.version_info[0] == 2 else self.aString)
            oprot.writeFieldEnd()
        if self.lint is not None:
            oprot.writeFieldBegin('lint', TType.LIST, 3)
            oprot.writeListBegin(TType.I32, len(self.lint))
            for iter62 in self.lint:
                oprot.writeI32(iter62)
            oprot.writeListEnd()
            oprot.writeFieldEnd()
        if self.lString is not None:
            oprot.writeFieldBegin('lString', TType.LIST, 4)
            oprot.writeListBegin(TType.STRING, len(self.lString))
            for iter63 in self.lString:
                oprot.writeString(iter63.encode('utf-8') if sys.version_info[0] == 2 else iter63)
            oprot.writeListEnd()
            oprot.writeFieldEnd()
        if self.lintString is not None:
            oprot.writeFieldBegin('lintString', TType.LIST, 5)
            oprot.writeListBegin(TType.STRUCT, len(self.lintString))
            for iter64 in self.lintString:
                iter64.write(oprot)
            oprot.writeListEnd()
            oprot.writeFieldEnd()
        if self.mStringString is not None:
            oprot.writeFieldBegin('mStringString', TType.MAP, 6)
            oprot.writeMapBegin(TType.STRING, TType.STRING, len(self.mStringString))
            for kiter65, viter66 in self.mStringString.items():
                oprot.writeString(kiter65.encode('utf-8') if sys.version_info[0] == 2 else kiter65)
                oprot.writeString(viter66.encode('utf-8') if sys.version_info[0] == 2 else viter66)
            oprot.writeMapEnd()
            oprot.writeFieldEnd()
        if self.attributes is not None:
            oprot.writeFieldBegin('attributes', TType.MAP, 7)
            oprot.writeMapBegin(TType.STRING, TType.MAP, len(self.attributes))
            for kiter67, viter68 in self.attributes.items():
                oprot.writeString(kiter67.encode('utf-8') if sys.version_info[0] == 2 else kiter67)
                oprot.writeMapBegin(TType.STRING, TType.MAP, len(viter68))
                for kiter69, viter70 in viter68.items():
                    oprot.writeString(kiter69.encode('utf-8') if sys.version_info[0] == 2 else kiter69)
                    oprot.writeMapBegin(TType.STRING, TType.STRUCT, len(viter70))
                    for kiter71, viter72 in viter70.items():
                        oprot.writeString(kiter71.encode('utf-8') if sys.version_info[0] == 2 else kiter71)
                        viter72.write(oprot)
                    oprot.writeMapEnd()
                oprot.writeMapEnd()
            oprot.writeMapEnd()
            oprot.writeFieldEnd()
        if self.unionField1 is not None:
            oprot.writeFieldBegin('unionField1', TType.STRUCT, 8)
            self.unionField1.write(oprot)
            oprot.writeFieldEnd()
        if self.unionField2 is not None:
            oprot.writeFieldBegin('unionField2', TType.STRUCT, 9)
            self.unionField2.write(oprot)
            oprot.writeFieldEnd()
        if self.unionField3 is not None:
            oprot.writeFieldBegin('unionField3', TType.STRUCT, 10)
            self.unionField3.write(oprot)
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        return

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)


class SetIntString(object):
    """
    Attributes:
     - sIntString
     - aString

    """


    def __init__(self, sIntString=None, aString=None,):
        self.sIntString = sIntString
        self.aString = aString

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 1:
                if ftype == TType.SET:
                    self.sIntString = set()
                    (_etype76, _size73) = iprot.readSetBegin()
                    for _i77 in range(_size73):
                        _elem78 = IntString()
                        _elem78.read(iprot)
                        self.sIntString.add(_elem78)
                    iprot.readSetEnd()
                else:
                    iprot.skip(ftype)
            elif fid == 2:
                if ftype == TType.STRING:
                    self.aString = iprot.readString().decode('utf-8', errors='replace') if sys.version_info[0] == 2 else iprot.readString()
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('SetIntString')
        if self.sIntString is not None:
            oprot.writeFieldBegin('sIntString', TType.SET, 1)
            oprot.writeSetBegin(TType.STRUCT, len(self.sIntString))
            for iter79 in self.sIntString:
                iter79.write(oprot)
            oprot.writeSetEnd()
            oprot.writeFieldEnd()
        if self.aString is not None:
            oprot.writeFieldBegin('aString', TType.STRING, 2)
            oprot.writeString(self.aString.encode('utf-8') if sys.version_info[0] == 2 else self.aString)
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        return

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)
all_structs.append(PropValueUnion)
PropValueUnion.thrift_spec = (
    None,  # 0
    (1, TType.I32, 'intValue', None, None, ),  # 1
    (2, TType.I64, 'longValue', None, None, ),  # 2
    (3, TType.STRING, 'stringValue', 'UTF8', None, ),  # 3
    (4, TType.DOUBLE, 'doubleValue', None, None, ),  # 4
    (5, TType.BOOL, 'flag', None, None, ),  # 5
    (6, TType.LIST, 'lString', (TType.STRING, 'UTF8', False), None, ),  # 6
    (7, TType.MAP, 'unionMStringString', (TType.STRING, 'UTF8', TType.STRING, 'UTF8', False), None, ),  # 7
)
all_structs.append(IntString)
IntString.thrift_spec = (
    None,  # 0
    (1, TType.I32, 'myint', None, None, ),  # 1
    (2, TType.STRING, 'myString', 'UTF8', None, ),  # 2
    (3, TType.I32, 'underscore_int', None, None, ),  # 3
)
all_structs.append(Complex)
Complex.thrift_spec = (
    None,  # 0
    (1, TType.I32, 'aint', None, None, ),  # 1
    (2, TType.STRING, 'aString', 'UTF8', None, ),  # 2
    (3, TType.LIST, 'lint', (TType.I32, None, False), None, ),  # 3
    (4, TType.LIST, 'lString', (TType.STRING, 'UTF8', False), None, ),  # 4
    (5, TType.LIST, 'lintString', (TType.STRUCT, [IntString, None], False), None, ),  # 5
    (6, TType.MAP, 'mStringString', (TType.STRING, 'UTF8', TType.STRING, 'UTF8', False), None, ),  # 6
    (7, TType.MAP, 'attributes', (TType.STRING, 'UTF8', TType.MAP, (TType.STRING, 'UTF8', TType.MAP, (TType.STRING, 'UTF8', TType.STRUCT, [PropValueUnion, None], False), False), False), None, ),  # 7
    (8, TType.STRUCT, 'unionField1', [PropValueUnion, None], None, ),  # 8
    (9, TType.STRUCT, 'unionField2', [PropValueUnion, None], None, ),  # 9
    (10, TType.STRUCT, 'unionField3', [PropValueUnion, None], None, ),  # 10
)
all_structs.append(SetIntString)
SetIntString.thrift_spec = (
    None,  # 0
    (1, TType.SET, 'sIntString', (TType.STRUCT, [IntString, None], False), None, ),  # 1
    (2, TType.STRING, 'aString', 'UTF8', None, ),  # 2
)
fix_spec(all_structs)
del all_structs
