<?php
namespace metastore;

/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
use Thrift\Base\TBase;
use Thrift\Type\TType;
use Thrift\Type\TMessageType;
use Thrift\Exception\TException;
use Thrift\Exception\TProtocolException;
use Thrift\Protocol\TProtocol;
use Thrift\Protocol\TBinaryProtocolAccelerated;
use Thrift\Exception\TApplicationException;

final class TxnType
{
    const DEFAULT = 0;

    const REPL_CREATED = 1;

    const READ_ONLY = 2;

    const COMPACTION = 3;

    const MATER_VIEW_REBUILD = 4;

    static public $__names = array(
        0 => 'DEFAULT',
        1 => 'REPL_CREATED',
        2 => 'READ_ONLY',
        3 => 'COMPACTION',
        4 => 'MATER_VIEW_REBUILD',
    );
}

