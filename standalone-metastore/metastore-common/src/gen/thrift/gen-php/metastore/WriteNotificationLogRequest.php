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

class WriteNotificationLogRequest
{
    static public $isValidate = false;

    static public $_TSPEC = array(
        1 => array(
            'var' => 'txnId',
            'isRequired' => true,
            'type' => TType::I64,
        ),
        2 => array(
            'var' => 'writeId',
            'isRequired' => true,
            'type' => TType::I64,
        ),
        3 => array(
            'var' => 'db',
            'isRequired' => true,
            'type' => TType::STRING,
        ),
        4 => array(
            'var' => 'table',
            'isRequired' => true,
            'type' => TType::STRING,
        ),
        5 => array(
            'var' => 'fileInfo',
            'isRequired' => true,
            'type' => TType::STRUCT,
            'class' => '\metastore\InsertEventRequestData',
        ),
        6 => array(
            'var' => 'partitionVals',
            'isRequired' => false,
            'type' => TType::LST,
            'etype' => TType::STRING,
            'elem' => array(
                'type' => TType::STRING,
                ),
        ),
    );

    /**
     * @var int
     */
    public $txnId = null;
    /**
     * @var int
     */
    public $writeId = null;
    /**
     * @var string
     */
    public $db = null;
    /**
     * @var string
     */
    public $table = null;
    /**
     * @var \metastore\InsertEventRequestData
     */
    public $fileInfo = null;
    /**
     * @var string[]
     */
    public $partitionVals = null;

    public function __construct($vals = null)
    {
        if (is_array($vals)) {
            if (isset($vals['txnId'])) {
                $this->txnId = $vals['txnId'];
            }
            if (isset($vals['writeId'])) {
                $this->writeId = $vals['writeId'];
            }
            if (isset($vals['db'])) {
                $this->db = $vals['db'];
            }
            if (isset($vals['table'])) {
                $this->table = $vals['table'];
            }
            if (isset($vals['fileInfo'])) {
                $this->fileInfo = $vals['fileInfo'];
            }
            if (isset($vals['partitionVals'])) {
                $this->partitionVals = $vals['partitionVals'];
            }
        }
    }

    public function getName()
    {
        return 'WriteNotificationLogRequest';
    }


    public function read($input)
    {
        $xfer = 0;
        $fname = null;
        $ftype = 0;
        $fid = 0;
        $xfer += $input->readStructBegin($fname);
        while (true) {
            $xfer += $input->readFieldBegin($fname, $ftype, $fid);
            if ($ftype == TType::STOP) {
                break;
            }
            switch ($fid) {
                case 1:
                    if ($ftype == TType::I64) {
                        $xfer += $input->readI64($this->txnId);
                    } else {
                        $xfer += $input->skip($ftype);
                    }
                    break;
                case 2:
                    if ($ftype == TType::I64) {
                        $xfer += $input->readI64($this->writeId);
                    } else {
                        $xfer += $input->skip($ftype);
                    }
                    break;
                case 3:
                    if ($ftype == TType::STRING) {
                        $xfer += $input->readString($this->db);
                    } else {
                        $xfer += $input->skip($ftype);
                    }
                    break;
                case 4:
                    if ($ftype == TType::STRING) {
                        $xfer += $input->readString($this->table);
                    } else {
                        $xfer += $input->skip($ftype);
                    }
                    break;
                case 5:
                    if ($ftype == TType::STRUCT) {
                        $this->fileInfo = new \metastore\InsertEventRequestData();
                        $xfer += $this->fileInfo->read($input);
                    } else {
                        $xfer += $input->skip($ftype);
                    }
                    break;
                case 6:
                    if ($ftype == TType::LST) {
                        $this->partitionVals = array();
                        $_size810 = 0;
                        $_etype813 = 0;
                        $xfer += $input->readListBegin($_etype813, $_size810);
                        for ($_i814 = 0; $_i814 < $_size810; ++$_i814) {
                            $elem815 = null;
                            $xfer += $input->readString($elem815);
                            $this->partitionVals []= $elem815;
                        }
                        $xfer += $input->readListEnd();
                    } else {
                        $xfer += $input->skip($ftype);
                    }
                    break;
                default:
                    $xfer += $input->skip($ftype);
                    break;
            }
            $xfer += $input->readFieldEnd();
        }
        $xfer += $input->readStructEnd();
        return $xfer;
    }

    public function write($output)
    {
        $xfer = 0;
        $xfer += $output->writeStructBegin('WriteNotificationLogRequest');
        if ($this->txnId !== null) {
            $xfer += $output->writeFieldBegin('txnId', TType::I64, 1);
            $xfer += $output->writeI64($this->txnId);
            $xfer += $output->writeFieldEnd();
        }
        if ($this->writeId !== null) {
            $xfer += $output->writeFieldBegin('writeId', TType::I64, 2);
            $xfer += $output->writeI64($this->writeId);
            $xfer += $output->writeFieldEnd();
        }
        if ($this->db !== null) {
            $xfer += $output->writeFieldBegin('db', TType::STRING, 3);
            $xfer += $output->writeString($this->db);
            $xfer += $output->writeFieldEnd();
        }
        if ($this->table !== null) {
            $xfer += $output->writeFieldBegin('table', TType::STRING, 4);
            $xfer += $output->writeString($this->table);
            $xfer += $output->writeFieldEnd();
        }
        if ($this->fileInfo !== null) {
            if (!is_object($this->fileInfo)) {
                throw new TProtocolException('Bad type in structure.', TProtocolException::INVALID_DATA);
            }
            $xfer += $output->writeFieldBegin('fileInfo', TType::STRUCT, 5);
            $xfer += $this->fileInfo->write($output);
            $xfer += $output->writeFieldEnd();
        }
        if ($this->partitionVals !== null) {
            if (!is_array($this->partitionVals)) {
                throw new TProtocolException('Bad type in structure.', TProtocolException::INVALID_DATA);
            }
            $xfer += $output->writeFieldBegin('partitionVals', TType::LST, 6);
            $output->writeListBegin(TType::STRING, count($this->partitionVals));
            foreach ($this->partitionVals as $iter816) {
                $xfer += $output->writeString($iter816);
            }
            $output->writeListEnd();
            $xfer += $output->writeFieldEnd();
        }
        $xfer += $output->writeFieldStop();
        $xfer += $output->writeStructEnd();
        return $xfer;
    }
}
