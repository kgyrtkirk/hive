<?php
namespace metastore;

/**
 * Autogenerated by Thrift Compiler (0.14.1)
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

class PartitionsSpecByExprResult
{
    static public $isValidate = false;

    static public $_TSPEC = array(
        1 => array(
            'var' => 'partitionsSpec',
            'isRequired' => true,
            'type' => TType::LST,
            'etype' => TType::STRUCT,
            'elem' => array(
                'type' => TType::STRUCT,
                'class' => '\metastore\PartitionSpec',
                ),
        ),
        2 => array(
            'var' => 'hasUnknownPartitions',
            'isRequired' => true,
            'type' => TType::BOOL,
        ),
    );

    /**
     * @var \metastore\PartitionSpec[]
     */
    public $partitionsSpec = null;
    /**
     * @var bool
     */
    public $hasUnknownPartitions = null;

    public function __construct($vals = null)
    {
        if (is_array($vals)) {
            if (isset($vals['partitionsSpec'])) {
                $this->partitionsSpec = $vals['partitionsSpec'];
            }
            if (isset($vals['hasUnknownPartitions'])) {
                $this->hasUnknownPartitions = $vals['hasUnknownPartitions'];
            }
        }
    }

    public function getName()
    {
        return 'PartitionsSpecByExprResult';
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
                    if ($ftype == TType::LST) {
                        $this->partitionsSpec = array();
                        $_size445 = 0;
                        $_etype448 = 0;
                        $xfer += $input->readListBegin($_etype448, $_size445);
                        for ($_i449 = 0; $_i449 < $_size445; ++$_i449) {
                            $elem450 = null;
                            $elem450 = new \metastore\PartitionSpec();
                            $xfer += $elem450->read($input);
                            $this->partitionsSpec []= $elem450;
                        }
                        $xfer += $input->readListEnd();
                    } else {
                        $xfer += $input->skip($ftype);
                    }
                    break;
                case 2:
                    if ($ftype == TType::BOOL) {
                        $xfer += $input->readBool($this->hasUnknownPartitions);
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
        $xfer += $output->writeStructBegin('PartitionsSpecByExprResult');
        if ($this->partitionsSpec !== null) {
            if (!is_array($this->partitionsSpec)) {
                throw new TProtocolException('Bad type in structure.', TProtocolException::INVALID_DATA);
            }
            $xfer += $output->writeFieldBegin('partitionsSpec', TType::LST, 1);
            $output->writeListBegin(TType::STRUCT, count($this->partitionsSpec));
            foreach ($this->partitionsSpec as $iter451) {
                $xfer += $iter451->write($output);
            }
            $output->writeListEnd();
            $xfer += $output->writeFieldEnd();
        }
        if ($this->hasUnknownPartitions !== null) {
            $xfer += $output->writeFieldBegin('hasUnknownPartitions', TType::BOOL, 2);
            $xfer += $output->writeBool($this->hasUnknownPartitions);
            $xfer += $output->writeFieldEnd();
        }
        $xfer += $output->writeFieldStop();
        $xfer += $output->writeStructEnd();
        return $xfer;
    }
}
